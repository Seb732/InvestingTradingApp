package com.capgemini.investingtradingapp.entity;


import com.capgemini.investingtradingapp.exception.IncorrectEmailException;
import com.capgemini.investingtradingapp.exception.IncorrectTeleNumbException;
import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.InvalidAmountException;
import lombok.*;

import javax.persistence.*;

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
@Table(name = "users")
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PersonalAccount personalAccount = new PersonalAccount();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private InvestingAccount investingAccount = new InvestingAccount();

    /**
     * This metod enables user to transfer money to be invested using investing account.
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
