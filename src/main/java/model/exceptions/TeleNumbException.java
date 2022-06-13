package model.exceptions;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TeleNumbException extends Exception{
    public TeleNumbException(String message){
        super(message);
    }
}
