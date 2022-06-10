package model;


import lombok.*;
import model.Exceptions.ExceptionEmail;
import model.Exceptions.ExceptionTeleNumb;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    long id;
    String first_name;
    String last_name;
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
            assert true; // tbd
        }
    }

    public void transferOut(double amount){
        try{
            this.getInvestingAccount().withdraw(amount);
            this.getPersonalAccount().deposit(amount);
        }
        catch (IllegalAccessException | IllegalArgumentException ex) {
            assert true; // tbd
        }
    }

    public void setTeleNumb(String teleNumb) throws ExceptionTeleNumb {
        if (!Validation.validateTeleNumb(teleNumb)){
            throw new ExceptionTeleNumb("Incorrect phone number");
        }
        this.teleNumb = teleNumb;
    }

    public void setEmail(String email) throws ExceptionEmail {
        if (!Validation.validateEmail(email)){
            throw new ExceptionEmail("Incorrect email");
        }
        this.email = email;
    }

}
