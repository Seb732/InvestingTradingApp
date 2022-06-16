package model.entities;


import lombok.NoArgsConstructor;

@NoArgsConstructor
/**
 * Class for user's personal account. Content development in a progress
 */
public class PersonalAccount extends Account{

    public PersonalAccount(double balance) {
        super(balance);
    }
}
