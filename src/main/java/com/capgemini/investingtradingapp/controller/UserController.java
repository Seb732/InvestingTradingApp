package com.capgemini.investingtradingapp.controller;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor

@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDTO newUser(@RequestBody final UserDTO user) {
        return userService.save(user);
    }


    @GetMapping()
    public ResponseEntity<List<UserDTO>> getByFirstNameAndLastName(
            @RequestParam final Map<String, String> allParams) {
        if (allParams.containsKey("firstName") && allParams.containsKey("lastName")) {
            List<UserDTO> users = userService.findByFirstNameAndLastName(allParams.get("firstName"), allParams.get("lastName"));
            return !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (allParams.containsKey("teleNumb") && allParams.containsKey("email")) {
            List<UserDTO> users = userService.findByTeleNumbAndEmail(allParams.get("teleNumb"), allParams.get("email"));
            return !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDTO> users = userService.GetAll();
        return !users.isEmpty() ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
