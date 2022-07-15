package com.capgemini.investingtradingappuser.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IncorrectTeleNumbException extends Exception{
    public IncorrectTeleNumbException(String message){
        super(message);
    }
}
