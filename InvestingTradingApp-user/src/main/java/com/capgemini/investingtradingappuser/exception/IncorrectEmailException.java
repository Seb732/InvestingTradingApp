package com.capgemini.investingtradingappuser.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IncorrectEmailException extends Exception{
    public IncorrectEmailException(String message){
        super(message);
    }
}
