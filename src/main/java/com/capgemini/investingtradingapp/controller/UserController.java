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
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody final UserDTO user) {
        userService.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestParam final long id
    ) {
        userService.delete(id);
    }

    @PutMapping
    ResponseEntity<UserDTO> update(
            @RequestParam final String id

    ) {
        //TODO
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getByFirstNameAndLastName(
            @RequestParam final Map<String, String> allParams) {

        if (allParams.containsKey("firstName") && allParams.containsKey("lastName")) {

            return userService.findByFirstNameAndLastName(allParams.get("firstName"), allParams.get("lastName"));
        }


        if (allParams.containsKey("teleNumb") && allParams.containsKey("email")) {

            return userService.findByTeleNumbAndEmail(allParams.get("teleNumb"), allParams.get("email"));
        }

        return userService.getAll();
    }


}
