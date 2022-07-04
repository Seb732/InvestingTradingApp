package com.capgemini.investingtradingapp.controller;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody final UserDTO user) {
        userService.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam final long userID) {
        userService.delete(userID);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam final long userID, @RequestBody final UserDTO user) throws IncorrectEmailException, IncorrectTeleNumbException {
        userService.update(userID, user);
    }

    @GetMapping
    public List<UserDTO> read(@RequestParam final Map<String, String> allParams) {

        if (allParams.containsKey("firstName") && allParams.containsKey("lastName")) {
            return userService.findByFirstNameAndLastName(allParams.get("firstName"), allParams.get("lastName"));
        }

        if (allParams.containsKey("teleNumb") && allParams.containsKey("email")) {
            return userService.findByTeleNumbAndEmail(allParams.get("teleNumb"), allParams.get("email"));
        }

        return userService.getAll();
    }


}
