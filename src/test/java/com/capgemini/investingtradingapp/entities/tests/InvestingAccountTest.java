package com.capgemini.investingtradingapp.entities.tests;

import com.capgemini.investingtradingapp.entity.InvestingAccount;
import com.capgemini.investingtradingapp.entity.Position;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import com.capgemini.investingtradingapp.exception.PositionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvestingAccountTest {
    InvestingAccount investingAccount;

    @BeforeEach
    void setup(){
        investingAccount = new InvestingAccount();
    }

    @Test
    void is_starting_deposit_equal_to_zero(){
        assertEquals(0, investingAccount.getBalance());
    }


    @Test
    void deposit_increases_balance_correctly() throws InvalidAmountException {
        investingAccount.deposit(10);
        assertEquals(10,investingAccount.getBalance());
    }

    @Test
    void deposit_throws_invalid_amount_exception(){
        assertThrows(InvalidAmountException.class, () -> {
            investingAccount.deposit(0.5);
        });
    }

    @Test
    void withdraw_decreases_balance_correctly() throws InsufficientFoundsException, InvalidAmountException {
        investingAccount.deposit(10);
        investingAccount.withdraw(5);
        assertEquals(5,investingAccount.getBalance());
    }

    @Test
    void withdraw_throws_insufficient_founds_exception(){
        assertThrows(InsufficientFoundsException.class, () -> {
            investingAccount.withdraw(10);
        });
    }

    @Test
    void withdraw_throws_invalid_amount_exception(){
        assertThrows(InvalidAmountException.class, () -> {
            investingAccount.withdraw(-15);
        });
    }


    @Test
    void buy_throws_insufficient_founds_exception(){
       assertThrows(InsufficientFoundsException.class, () -> {
            investingAccount.buy(1,2,2,investingAccount);
        });
    }

    @Test
    void buy_adds_position_to_portfolio_correctly() throws InsufficientFoundsException, InvalidAmountException {
        investingAccount.deposit(10);
        investingAccount.buy(1,2,3,investingAccount);
        assertTrue(investingAccount.getPortfolio().size() > 0);
        assertSame(1L, investingAccount.getPortfolio().get(0).getCompanyID());
        assertEquals(2, investingAccount.getPortfolio().get(0).getSize());
        assertEquals(3, investingAccount.getPortfolio().get(0).getTicker());
        assertEquals(investingAccount, investingAccount.getPortfolio().get(0).getInvestingAccount());
    }

    @Test
    void buy_decreases_balance_after_successful_addition_correctly() throws InsufficientFoundsException, InvalidAmountException {
        investingAccount.deposit(10);
        investingAccount.buy(1,2,3,investingAccount);
        assertEquals(4,investingAccount.getBalance());
    }

    @Test
    void sell_throws_position_not_found_exception() throws InsufficientFoundsException, InvalidAmountException {
        Position position = new Position();
        //throwing exception while portfolio is empty
        assertThrows(PositionNotFoundException.class, () -> {
            investingAccount.sell(position);
        });
        //throwing exception while position is not in portfolio and portfolio is not empty
        investingAccount.deposit(10);
        investingAccount.buy(1,2,3,investingAccount);
        assertThrows(PositionNotFoundException.class, () -> {
            investingAccount.sell(position);
        });
    }

    @Test
    void sell_increases_balance_correctly() throws InsufficientFoundsException, PositionNotFoundException, InvalidAmountException {
        investingAccount.deposit(10);
        investingAccount.buy(1,2,3, investingAccount);
        investingAccount.sell(investingAccount.getPortfolio().get(0));
        assertEquals(10,investingAccount.getBalance());
    }

    @Test
    void sell_removes_position_from_portfolio_correctly() throws InsufficientFoundsException, PositionNotFoundException, InvalidAmountException {
        investingAccount.deposit(10);
        investingAccount.buy(1,2,3, investingAccount);
        investingAccount.sell(investingAccount.getPortfolio().get(0));
        assertEquals(0,investingAccount.getPortfolio().size());
    }





}
