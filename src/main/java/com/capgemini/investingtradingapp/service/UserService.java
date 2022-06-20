package com.capgemini.investingtradingapp.service;

import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Iterable<User> GetAll(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
}
