package model;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersonalAccount extends Account{
    
    public PersonalAccount(double balance) {
        super(balance);
    }
}
