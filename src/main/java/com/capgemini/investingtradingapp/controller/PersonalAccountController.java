package com.capgemini.investingtradingapp.controller;


import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import com.capgemini.investingtradingapp.service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void depositOrWithdraw(@RequestParam final String operationType, @PathVariable final long personalAccountID, @RequestParam final double amount) throws InvalidAmountException, InsufficientFoundsException {

        if (Objects.equals(operationType, "deposit")) {
            personalAccountService.deposit(personalAccountID, amount);
        }

        if (Objects.equals(operationType, "withdrawal")) {
            personalAccountService.withdraw(personalAccountID, amount);
        }

        if (Objects.equals(operationType, "transferIN")) {
            personalAccountService.transferIN(personalAccountID, amount);
        }
    }


}
