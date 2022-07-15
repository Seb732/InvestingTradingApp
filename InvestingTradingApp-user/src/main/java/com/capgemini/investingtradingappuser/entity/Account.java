package com.capgemini.investingtradingappuser.entity;


import com.capgemini.investingtradingappuser.exception.InsufficientFoundsException;
import com.capgemini.investingtradingappuser.exception.InvalidAmountException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * Abstract class for user's account
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Account {
    /**
     * the account balance field
     */
    @Column(name = "balance")
    protected double balance;

    /**
     * This method enables user to deposit money
     * @param amount - amount of money to be deposited
     */
    public void deposit(double amount) throws InvalidAmountException {
        if (amount > 1){
            this.balance += amount;
        }
        else{
            throw new InvalidAmountException("Amount deposited has to be bigger than 1");
        }
    }

    /**
     * This method enables user to withdraw money from account
     * @param amount - amount of money to be withdrawn
     * @throws InsufficientFoundsException - if the amount is overdrawn, then method throws exception
     */
    public void withdraw(double amount) throws InsufficientFoundsException, InvalidAmountException {
        if (amount <= this.balance && amount >= 0){
            this.balance -= amount;
        }
        else if(amount < 0) {
            throw new InvalidAmountException("Amount has to be bigger than 0");
        }
        else {
            throw new InsufficientFoundsException("Not enough balance");
        }
    }
}
