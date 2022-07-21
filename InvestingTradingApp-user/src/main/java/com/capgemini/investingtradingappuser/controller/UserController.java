package com.capgemini.investingtradingappuser.controller;

import com.capgemini.investingtradingappuser.exception.IncorrectEmailException;
import com.capgemini.investingtradingappuser.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingappuser.service.UserService;
import com.capgemini.investingtradingappuserclient.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.redis.core.RedisTemplate;
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

    private static final String CIRCUIT_SERVICE = "userService";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody final UserDTO user) {

        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> userService.save(user));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam final long userID) {

        userService.delete(userID);
    }

    @PutMapping("/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final long userID, @Valid @RequestBody final UserDTO user) {
        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
            try {
                return userService.update(userID, user);
            } catch (IncorrectTeleNumbException | IncorrectEmailException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @GetMapping
    public List<UserDTO> read(@RequestParam final Map<String, String> allParams) {

        if (allParams.containsKey("firstName") && allParams.containsKey("lastName")) {
            return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> userService.findByFirstNameAndLastName(allParams.get("firstName"), allParams.get("lastName")));
        }

        if (allParams.containsKey("teleNumb") && allParams.containsKey("email")) {
            return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> userService.findByTeleNumbAndEmail(allParams.get("teleNumb"), allParams.get("email")));
        }

        return circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> userService.getAll());
    }

}
