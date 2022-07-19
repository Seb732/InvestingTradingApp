package com.capgemini.investingtradingappuser.service;

import com.capgemini.investingtradingappuser.entity.User;
import com.capgemini.investingtradingappuser.exception.IncorrectEmailException;
import com.capgemini.investingtradingappuser.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingappuser.repository.UserRepository;
import com.capgemini.investingtradingappuserclient.dto.UserDTO;
import com.capgemini.investingtradingappuserclient.event.UserDeletedEvent;
import com.capgemini.investingtradingappuserclient.event.UserRegisteredEvent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @CachePut(value = "users", keyGenerator = "customKeyGenerator")
    public User save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.getInvestingAccount().setUser(user);
        user.getPersonalAccount().setUser(user);
        kafkaTemplate.send("user-create", user.getEmail(),
                new UserRegisteredEvent(user.getFirstName(), user.getLastName(), user.getEmail(), user.getTeleNumb()));
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", keyGenerator = "customKeyGenerator")
    public void delete(long userID) {
        User user = userRepository.findById(userID).get();
        user.setActivityStatus(false);
        kafkaTemplate.send("user-delete", user.getEmail(),
                new UserDeletedEvent(user.getFirstName(), user.getLastName(), user.getEmail(), user.getTeleNumb()));
        userRepository.save(user);
    }

    @CachePut(value = "users", keyGenerator = "customKeyGenerator")
    public User update(long userID, UserDTO userDTO) throws IncorrectTeleNumbException, IncorrectEmailException {
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

        return userRepository.save(user);
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
