package com.capgemini.investingtradingapp.entity;

import lombok.NoArgsConstructor;

import java.util.regex.Pattern;


@NoArgsConstructor
/**
 * Utility class with field validation methods.
 */
public abstract class Validation {

    /**
     * Validate user's phone number using regular expression.
     * @param teleNumb - user's phone number
     * @return - boolean, depends on the result of validation
     */
    public static boolean validateTeleNumb(String teleNumb){return Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{3}$").matcher(teleNumb).matches();}

    /**
     * Validate user's email using regular expression.
     * @param email
     * @return - boolean, depends on the result of validation
     */
    public static boolean validateEmail(String email){return Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$").matcher(email).matches();}

}
