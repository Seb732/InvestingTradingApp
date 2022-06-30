package com.capgemini.investingtradingapp.entities.tests;

import com.capgemini.investingtradingapp.entity.PersonalAccount;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalAccountTest {

    PersonalAccount personalAccount;

    @BeforeEach
    void setup(){
        personalAccount = new PersonalAccount();
    }

    @Test
    void constructor_with_arguments_works_properly() throws InvalidAmountException {
        this.personalAccount.deposit(100);
        assertEquals(100, personalAccount.getBalance());
    }
}
