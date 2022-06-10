package model.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExceptionEmail extends Exception{
    public ExceptionEmail(String message){
        super(message);
    }
}
