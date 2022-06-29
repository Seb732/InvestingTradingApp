package com.capgemini.investingtradingapp.controller;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor

@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDTO newUser(@RequestBody final UserDTO user) {
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserByFirstNameAndLastName(
            @RequestParam final String firstName,
            @RequestParam final String lastName
    ) {
        Optional<UserDTO> userDTO = userService.findByFirstNameAndLastName(firstName, lastName);
        return userDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
