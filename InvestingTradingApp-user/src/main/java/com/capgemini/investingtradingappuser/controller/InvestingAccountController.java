package com.capgemini.investingtradingappuser.controller;


import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import com.capgemini.investingtradingappuser.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import com.capgemini.investingtradingappuser.exception.PositionNotFoundException;
import com.capgemini.investingtradingappuser.service.InvestingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
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
@RequestMapping("/user/investingAccount")
public class InvestingAccountController {

    private static final String CIRCUIT_SERVICE = "investingAccountService";
    @Autowired
    private InvestingAccountService investingAccountService;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyPosition(@Valid @RequestBody final PositionDTO positionDTO) {
        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
            try {
                investingAccountService.buyPosition(positionDTO);
            } catch (com.capgemini.investingtradingappposition.exception.InsufficientFoundsException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @PutMapping("/position/{positionID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellPosition(@PathVariable final long positionID) {
        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
            try {
                investingAccountService.sellPosition(positionID);
            } catch (PositionNotFoundException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @PutMapping("/{investingAccountID}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferOUT(@PathVariable final long investingAccountID, @RequestParam final double amount) {
        circuitBreakerFactory.create(CIRCUIT_SERVICE).run(() -> {
            try {
                investingAccountService.transferOUT(investingAccountID, amount);
            } catch (InvalidAmountException | InsufficientFoundsException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }
}
