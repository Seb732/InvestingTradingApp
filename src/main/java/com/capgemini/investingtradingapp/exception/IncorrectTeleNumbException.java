package com.capgemini.investingtradingapp.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IncorrectTeleNumbException extends Exception{
    public IncorrectTeleNumbException(String message){
        super(message);
    }
}
