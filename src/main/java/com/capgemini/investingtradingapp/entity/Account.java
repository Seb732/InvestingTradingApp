package com.capgemini.investingtradingapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    double balance;

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
     * @throws IllegalAccessException - if the amount is overdrawn, then method throws exception
     */
    public void withdraw(double amount) throws Exception {
        if (amount <= this.balance){
            this.balance -= amount;
        }
        else{
            throw new Exception("Overdraft!");
        }
    }
}
