package com.capgemini.investingtradingapp.entity;


import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

/**
 * This class represents user's entity. Stores all information fields required. Contains method which enables user
 * to transfer money from personal to investing account and vice-versa.
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "first_name_last_name_index", columnList = "first_name, last_name"),
        @Index(name = "tele_numb_email_index", columnList = "tele_numb, email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userID;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "tele_numb", nullable = false)
    private String teleNumb;
    @Column(name = "email", nullable = false)
    private String email;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PersonalAccount personalAccount = new PersonalAccount();

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private InvestingAccount investingAccount = new InvestingAccount();

    /**
     * This method enables user to transfer money to be invested using investing account.
     *
     * @param amount - amount of money to be transferred
     */
    public void transferIN(double amount) throws InsufficientFoundsException, InvalidAmountException {
        this.getPersonalAccount().withdraw(amount);
        this.getInvestingAccount().deposit(amount);
    }

    /**
     * This method enables user to withdraw money from investing account.
     * @param amount - amount of money to transferred out of the investing account
     */
    public void transferOut(double amount) throws InsufficientFoundsException, InvalidAmountException {
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
