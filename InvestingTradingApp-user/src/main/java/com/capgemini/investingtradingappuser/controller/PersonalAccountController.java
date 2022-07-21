package com.capgemini.investingtradingappuser.controller;


import com.capgemini.investingtradingappuser.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import com.capgemini.investingtradingappuser.service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user/personalAccount/{personalAccountID}")
public class PersonalAccountController {

    @Autowired
    private PersonalAccountService personalAccountService;

    private static final String CIRCUIT_SERVICE = "personalAccountService";
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @PutMapping("/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void depositOrWithdraw(@RequestParam final String operationType, @PathVariable final long personalAccountID, @RequestParam final double amount) {

        if (Objects.equals(operationType, "deposit")) {
            circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
                try {
                    personalAccountService.deposit(personalAccountID, amount);
                } catch (InvalidAmountException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        }

        if (Objects.equals(operationType, "withdrawal")) {
            circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
                try {
                    personalAccountService.withdraw(personalAccountID, amount);
                } catch (InvalidAmountException |
                         com.capgemini.investingtradingappposition.exception.InsufficientFoundsException |
                         InsufficientFoundsException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        }

        if (Objects.equals(operationType, "transferIN")) {
            circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
                try {
                    personalAccountService.transferIN(personalAccountID, amount);
                } catch (InvalidAmountException |
                         com.capgemini.investingtradingappposition.exception.InsufficientFoundsException |
                         InsufficientFoundsException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        }
    }


}
