package com.capgemini.investingtradingappuser.controller;

import com.capgemini.investingtradingappuser.exception.IncorrectEmailException;
import com.capgemini.investingtradingappuser.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingappuser.service.UserService;
import com.capgemini.investingtradingappuserclient.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody final UserDTO user) {
        userService.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam final long userID) {
        userService.delete(userID);
    }

    @PutMapping("/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final long userID, @Valid @RequestBody final UserDTO user) throws IncorrectEmailException, IncorrectTeleNumbException {
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
