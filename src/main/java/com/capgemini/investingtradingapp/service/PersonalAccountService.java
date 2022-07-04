package com.capgemini.investingtradingapp.service;


import com.capgemini.investingtradingapp.entity.PersonalAccount;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import com.capgemini.investingtradingapp.repository.PersonalAccountRepository;
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

    public void withdraw(long personalAccountID, double amount) throws InvalidAmountException, InsufficientFoundsException {
        PersonalAccount personalAccount = personalAccountRepository.findById(personalAccountID).get();
        personalAccount.withdraw(amount);
        personalAccountRepository.save(personalAccount);
    }

    public void transferIN(long personalAccountID, double amount) throws InvalidAmountException, InsufficientFoundsException {
        PersonalAccount personalAccount = personalAccountRepository.findById(personalAccountID).get();
        personalAccount.getUser().transferIN(amount);
        personalAccountRepository.save(personalAccount);
    }

}
