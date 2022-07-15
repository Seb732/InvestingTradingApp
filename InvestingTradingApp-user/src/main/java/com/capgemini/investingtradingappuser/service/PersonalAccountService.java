package com.capgemini.investingtradingappuser.service;


import com.capgemini.investingtradingappposition.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.entity.PersonalAccount;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import com.capgemini.investingtradingappuser.repository.PersonalAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalAccountService {

    @Autowired
    private PersonalAccountRepository personalAccountRepository;


    public void deposit(long personalAccountID, double amount) throws InvalidAmountException {
        PersonalAccount personalAccount = personalAccountRepository.findById(personalAccountID).get();
        personalAccount.deposit(amount);
        personalAccountRepository.save(personalAccount);
    }

    public void withdraw(long personalAccountID, double amount) throws InvalidAmountException, InsufficientFoundsException, com.capgemini.investingtradingappuser.exception.InsufficientFoundsException {
        PersonalAccount personalAccount = personalAccountRepository.findById(personalAccountID).get();
        personalAccount.withdraw(amount);
        personalAccountRepository.save(personalAccount);
    }

    public void transferIN(long personalAccountID, double amount) throws InvalidAmountException, InsufficientFoundsException, com.capgemini.investingtradingappuser.exception.InsufficientFoundsException {
        PersonalAccount personalAccount = personalAccountRepository.findById(personalAccountID).get();
        personalAccount.getUser().transferIN(amount);
        personalAccountRepository.save(personalAccount);
    }

}
