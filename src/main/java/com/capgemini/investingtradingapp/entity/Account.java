package com.capgemini.investingtradingapp.entity;


import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

/**
 * Abstract class for user's account
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Account {
    /**
     * the account balance field
     */

    protected double balance;

    /**
     * This method enables user to deposit money
     * @param amount - amount of money to be deposited
     */
    public void deposit(double amount){
        if (amount > 1){
            this.balance += amount;
        }
    }

    /**
     * This method enables user to withdraw money from account
     * @param amount - amount of money to be withdrawn
     * @throws InsufficientFoundsException - if the amount is overdrawn, then method throws exception
     */
    public void withdraw(double amount) throws InsufficientFoundsException {
        if (amount <= this.balance && amount >= 0){
            this.balance -= amount;
        }
        else{
            throw new InsufficientFoundsException("Not enough balance");
        }
    }
}
