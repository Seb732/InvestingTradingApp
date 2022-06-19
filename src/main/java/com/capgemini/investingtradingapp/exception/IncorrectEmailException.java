package com.capgemini.investingtradingapp.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IncorrectEmailException extends Exception{
    public IncorrectEmailException(String message){
        super(message);
    }
}
