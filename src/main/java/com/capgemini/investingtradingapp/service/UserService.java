package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.exception.UserNotFoundException;
import com.capgemini.investingtradingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.getInvestingAccount().setUser(user);
        user.getPersonalAccount().setUser(user);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public void delete(long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User with ID: " + id + "has not been found");
        } else {
            userRepository.deleteById(id);
        }
    }

    public void update(long id, UserDTO userDTO) {
        //TODO
    }

    public List<UserDTO> GetAll() {
        return userRepository.findAll().stream().map(x -> modelMapper.map(x, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findUserByFirstNameAndLastName(firstName, lastName).stream().map(x -> modelMapper.map(x, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> findByTeleNumbAndEmail(String teleNumb, String email) {
        return userRepository.findUserByTeleNumbAndEmail(teleNumb, email).stream().map(x -> modelMapper.map(x, UserDTO.class)).collect(Collectors.toList());
    }
}
