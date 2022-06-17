package com.capgemini.investingtradingapp.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailException extends Exception{
    public EmailException(String message){
        super(message);
    }
}
