package com.capgemini.investingtradingapp.controller;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor

@RequestMapping("/employee")
public class UserController {
    private final UserService userService;


    @PostMapping
    public UserDTO newUser(@RequestBody final UserDTO user) {
        return userService.save(user);
    }

    @GetMapping
    @ResponseBody
    public Optional<UserDTO> getUserByFirstNameAndLastName(
            @RequestParam final String firstName,
            @RequestParam final String lastName
    ) {
        return userService.findByFirstNameAndLastName(firstName, lastName);
    }
}
