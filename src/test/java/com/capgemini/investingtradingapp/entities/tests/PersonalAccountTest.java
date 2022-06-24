package com.capgemini.investingtradingapp.entities.tests;

import com.capgemini.investingtradingapp.entity.PersonalAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalAccountTest {

    PersonalAccount personalAccount;

    @BeforeEach
    void setup(){
        personalAccount = new PersonalAccount();
    }

    @Test
    void constructor_with_arguments_works_properly(){
        personalAccount = new PersonalAccount(2);
        assertEquals(2,personalAccount.getBalance());
    }
}
