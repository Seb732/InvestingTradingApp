package com.capgemini.investingtradingapp.controller;


import com.capgemini.investingtradingapp.dto.PositionDTO;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import com.capgemini.investingtradingapp.exception.PositionNotFoundException;
import com.capgemini.investingtradingapp.service.InvestingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/investingAccount")
public class InvestingAccountController {

    @Autowired
    InvestingAccountService investingAccountService;

    @PostMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyPostion(@RequestParam final long investingAccountID, @Valid @RequestBody final PositionDTO positionDTO) throws InsufficientFoundsException {
        investingAccountService.buyPosition(investingAccountID, positionDTO);
    }

    @PutMapping("/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellPosition(@RequestParam final long investingAccountID, @RequestParam final long positionID) throws PositionNotFoundException {
        investingAccountService.sellPosition(investingAccountID, positionID);
    }

    @PutMapping("/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferOUT(@RequestParam final long investingAccountID, @RequestParam final double amount) throws InvalidAmountException, InsufficientFoundsException {
        investingAccountService.transferOUT(investingAccountID, amount);
    }
}
