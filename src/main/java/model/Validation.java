package model;

import java.util.regex.Pattern;

public abstract class Validation {
    public Validation() {}

    public static boolean validateTeleNumb(String teleNumb){return Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{3}$").matcher(teleNumb).matches();}
    public static boolean validateEmail(String email){return Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$").matcher(email).matches();}

}
