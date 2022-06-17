package com.capgemini.investingtradingapp.entity;


import com.capgemini.investingtradingapp.exception.EmailException;
import com.capgemini.investingtradingapp.exception.TeleNumbException;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode


/**
 * This class represents user's entity. Stores all information fields required. Contains method which enables user
 * to transfer money from personal to investing account and vice-versa.
 */
public class User {
    long id;
    String firstName;
    String lastName;
    String teleNumb;
    String email;
    final PersonalAccount personalAccount = new PersonalAccount();
    final InvestingAccount investingAccount = new InvestingAccount();

    /**
     * This metod enables user to transfer money to be invested using investing account.
     * @param amount - amount of money to be transferred
     */
    public void transferIN(double amount){
        try{
            this.getPersonalAccount().withdraw(amount);
            this.getInvestingAccount().deposit(amount);
        }
        catch (Exception ex){
            assert true; //TODO
        }
    }

    /**
     * This method enables user to withdraw money from investing account.
     * @param amount - amount of money to transferred out of the investing account
     */
    public void transferOut(double amount){
        try{
            this.getInvestingAccount().withdraw(amount);
            this.getPersonalAccount().deposit(amount);
        }
        catch (Exception ex) {
            assert true; //TODO
        }
    }

    /**
     * This method validates the correctness of user's phone number.
     * @param teleNumb - user's phone number
     * @throws TeleNumbException - if phone number is not correct then custom exception is being thrown
     */
    public void setTeleNumb(String teleNumb) throws TeleNumbException {
        if (!Validation.validateTeleNumb(teleNumb)){
            throw new TeleNumbException("Incorrect phone number");
        }
        this.teleNumb = teleNumb;
    }

    /**
     * This method validates the correctness of user's email.
     * @param email - user's email
     * @throws EmailException - if email is not correct then custom exception is being thrown
     */
    public void setEmail(String email) throws EmailException {
        if (!Validation.validateEmail(email)){
            throw new EmailException("Incorrect email");
        }
        this.email = email;
    }

}
