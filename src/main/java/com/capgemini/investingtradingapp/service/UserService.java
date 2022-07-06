package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @CachePut(value = "users", keyGenerator = "customKeyGenerator")
    public void save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.getInvestingAccount().setUser(user);
        user.getPersonalAccount().setUser(user);
        userRepository.save(user);
    }

    @CacheEvict(value = "users", keyGenerator = "customKeyGenerator")
    public void delete(long userID) {
        userRepository.deleteById(userID);
    }

    @CachePut(value = "users", keyGenerator = "customKeyGenerator")
    public void update(long userID, UserDTO userDTO) throws IncorrectTeleNumbException, IncorrectEmailException {
        User user = userRepository.findById(userID).get();
        User user1 = modelMapper.map(userDTO, User.class);
        if (user1.getFirstName() != null) {
            user.setFirstName(user1.getFirstName());
        }
        if (user1.getLastName() != null) {
            user.setLastName(user1.getLastName());
        }
        if (user1.getTeleNumb() != null) {
            user.setTeleNumb(user1.getTeleNumb());
        }
        if (user1.getEmail() != null) {
            user.setEmail(user1.getEmail());
        }

        userRepository.save(user);
    }

    @Cacheable(value = "users", keyGenerator = "customKeyGenerator")
    public List<UserDTO> getAll() {
        return mapAll(userRepository.findAll());
    }

    @Cacheable(value = "users", keyGenerator = "customKeyGenerator")
    public List<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        return mapAll(userRepository.findUserByFirstNameAndLastName(firstName, lastName));
    }

    @Cacheable(value = "users", keyGenerator = "customKeyGenerator")
    public List<UserDTO> findByTeleNumbAndEmail(String teleNumb, String email) {
        return mapAll(userRepository.findUserByTeleNumbAndEmail(teleNumb, email));
    }

    private List<UserDTO> mapAll(List<User> users) {
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
    }

}
