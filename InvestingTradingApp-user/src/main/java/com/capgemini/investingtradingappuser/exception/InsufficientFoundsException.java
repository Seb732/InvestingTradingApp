package com.capgemini.investingtradingappuser.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsufficientFoundsException extends Exception{
    public InsufficientFoundsException(String message){
        super(message);
    }
}
