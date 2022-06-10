package model.Exceptions;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExceptionTeleNumb extends Exception{
    public ExceptionTeleNumb(String message){
        super(message);
    }
}
