package com.capgemini.investingtradingapp.entity;


import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


/**
 * This class represents user's entity. Stores all information fields required. Contains method which enables user
 * to transfer money from personal to investing account and vice-versa.
 */
@Entity
@Table(name = "user_")
public class User {
    @Id
    @Column(name = "userid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    final long id = 0; // default value, the relevant is set directly in database
    @Column(name = "firstname", nullable = false)
    String firstName;
    @Column(name = "lastname", nullable = false)
    String lastName;
    @Column(name = "telenumb", nullable = false)
    String teleNumb;
    @Column(name = "email", nullable = false)
    String email;

    @Transient
    final PersonalAccount personalAccount = new PersonalAccount();
    @Transient
    final InvestingAccount investingAccount = new InvestingAccount();

    /**
     * This metod enables user to transfer money to be invested using investing account.
     * @param amount - amount of money to be transferred
     */
    public void transferIN(double amount) throws InsufficientFoundsException {
        this.getPersonalAccount().withdraw(amount);
        this.getInvestingAccount().deposit(amount);
    }

    /**
     * This method enables user to withdraw money from investing account.
     * @param amount - amount of money to transferred out of the investing account
     */
    public void transferOut(double amount) throws InsufficientFoundsException {
        this.getInvestingAccount().withdraw(amount);
        this.getPersonalAccount().deposit(amount);
    }

    /**
     * This method validates the correctness of user's phone number.
     * @param teleNumb - user's phone number
     * @throws IncorrectTeleNumbException - if phone number is not correct then custom exception is being thrown
     */
    public void setTeleNumb(String teleNumb) throws IncorrectTeleNumbException {
        if (!Validation.validateTeleNumb(teleNumb)){
            throw new IncorrectTeleNumbException("Incorrect phone number");
        }
        this.teleNumb = teleNumb;
    }

    /**
     * This method validates the correctness of user's email.
     * @param email - user's email
     * @throws IncorrectEmailException - if email is not correct then custom exception is being thrown
     */
    public void setEmail(String email) throws IncorrectEmailException {
        if (!Validation.validateEmail(email)){
            throw new IncorrectEmailException("Incorrect email");
        }
        this.email = email;
    }

}
