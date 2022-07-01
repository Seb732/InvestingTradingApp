package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.getInvestingAccount().setUser(user);
        user.getPersonalAccount().setUser(user);
        userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void update(long id, UserDTO userDTO) throws IncorrectTeleNumbException, IncorrectEmailException {
        User user = userRepository.findById(id).get();
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

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findUserByFirstNameAndLastName(firstName, lastName).stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> findByTeleNumbAndEmail(String teleNumb, String email) {
        return userRepository.findUserByTeleNumbAndEmail(teleNumb, email).stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}
