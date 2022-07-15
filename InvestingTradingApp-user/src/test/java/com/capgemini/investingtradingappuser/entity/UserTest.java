package com.capgemini.investingtradingappuser.entity;

import com.capgemini.investingtradingappposition.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.IncorrectEmailException;
import com.capgemini.investingtradingappuser.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    User user;

    @BeforeEach
    void setup() {
        user = new User();
    }

    @Test
    void transfer_in_throws_insufficient_founds_exception() {
        assertThrows(InsufficientFoundsException.class, () -> {
            user.transferIN(10);
        });
    }

    @Test
    void transfer_in_throws_invalid_amount_exception() {
        assertThrows(InvalidAmountException.class, () -> {
            user.transferIN(-10);
        });
    }

    @Test
    void transfer_in_correctly_transfers_balance() throws InvalidAmountException, InsufficientFoundsException, com.capgemini.investingtradingappuser.exception.InsufficientFoundsException {
        user.getPersonalAccount().deposit(10);
        user.transferIN(10);
        assertEquals(user.getPersonalAccount().getBalance(), 0);
        assertEquals(user.getInvestingAccount().getBalance(), 10);
    }

    @Test
    void transfer_out_throws_insufficient_founds_exception() {
        assertThrows(InsufficientFoundsException.class, () -> {
            user.transferOut(10);
        });
    }

    @Test
    void transfer_out_throws_invalid_amount_exception() {
        assertThrows(InvalidAmountException.class, () -> {
            user.transferOut(-10);
        });
    }

    @Test
    void transfer_out_correctly_transfers_balance() throws InvalidAmountException, InsufficientFoundsException, com.capgemini.investingtradingappuser.exception.InsufficientFoundsException {
        user.getInvestingAccount().deposit(10);
        user.transferOut(10);
        assertEquals(user.getPersonalAccount().getBalance(), 10);
        assertEquals(user.getInvestingAccount().getBalance(), 0);
    }

    @Test
    void set_tele_numb_throws_incorrect_tele_numb_exception() {
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("123456789");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("asdsadsda");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("123-193-1a1");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("123-193-90");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb(" ");
        });
    }

    @Test
    void set_tele_numb_correctly_sets_tele_numb() throws IncorrectTeleNumbException {
        user.setTeleNumb("123-456-789");
        assertEquals(user.getTeleNumb(), "123-456-789");
    }

    @Test
    void set_email_throws_incorrect_email_exception() {
        assertThrows(IncorrectEmailException.class, () -> {
            user.setEmail(" ");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("asdsadsdagmail.com");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("123-193-1a1@");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb("123-193-90###!!@gmail.com");
        });
        assertThrows(IncorrectTeleNumbException.class, () -> {
            user.setTeleNumb(" ");
        });
    }

    @Test
    void set_email_correctly_sets_email() throws IncorrectEmailException {
        user.setEmail("testEmail123@gmail.com");
        assertEquals(user.getEmail(), "testEmail123@gmail.com");
    }
}
