package com.capgemini.investingtradingappuser.controller;


import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import com.capgemini.investingtradingappuser.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import com.capgemini.investingtradingappuser.exception.PositionNotFoundException;
import com.capgemini.investingtradingappuser.service.InvestingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/investingAccount/{investingAccountID}")
public class InvestingAccountController {

    @Autowired
    InvestingAccountService investingAccountService;

//    @PostMapping("/position")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void buyPosition(@PathVariable final long investingAccountID, @Valid @RequestBody final PositionDTO positionDTO) throws InsufficientFoundsException {
//        investingAccountService.buyPosition(investingAccountID, positionDTO);
//    }

//    @PutMapping("/position/{positionID}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void sellPosition(@PathVariable final long investingAccountID, @PathVariable final long positionID) throws PositionNotFoundException {
//        investingAccountService.sellPosition(investingAccountID, positionID);
//    }

    @PutMapping("/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferOUT(@PathVariable final long investingAccountID, @RequestParam final double amount) throws InvalidAmountException, InsufficientFoundsException {
        investingAccountService.transferOUT(investingAccountID, amount);
    }
}
