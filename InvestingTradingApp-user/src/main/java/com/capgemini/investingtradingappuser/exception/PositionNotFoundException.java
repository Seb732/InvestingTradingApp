package com.capgemini.investingtradingappuser.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PositionNotFoundException extends Exception{
    public PositionNotFoundException(String message) {
        super(message);
    }
}
