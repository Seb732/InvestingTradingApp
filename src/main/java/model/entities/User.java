package model.entities;


import lombok.*;
import model.exceptions.EmailException;
import model.exceptions.TeleNumbException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    long id;
    String firstName;
    String lastName;
    String teleNumb;
    String email;
    PersonalAccount personalAccount;
    InvestingAccount investingAccount;


    public void transferIN(double amount){
        try{
            this.getPersonalAccount().withdraw(amount);
            this.getInvestingAccount().deposit(amount);
        }
        catch (IllegalArgumentException | IllegalAccessException ex){
            assert true; //TODO
        }
    }

    public void transferOut(double amount){
        try{
            this.getInvestingAccount().withdraw(amount);
            this.getPersonalAccount().deposit(amount);
        }
        catch (IllegalAccessException | IllegalArgumentException ex) {
            assert true; //TODO
        }
    }

    public void setTeleNumb(String teleNumb) throws TeleNumbException {
        if (!Validation.validateTeleNumb(teleNumb)){
            throw new TeleNumbException("Incorrect phone number");
        }
        this.teleNumb = teleNumb;
    }

    public void setEmail(String email) throws EmailException {
        if (!Validation.validateEmail(email)){
            throw new EmailException("Incorrect email");
        }
        this.email = email;
    }

}
