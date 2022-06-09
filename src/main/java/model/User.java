package model;

public class User {
    long id;
    String first_name;
    String last_name;
    String teleNumb;
    String email;

    PersonalAccount personalAccount;
    InvestingAccount investingAccount;


    public User() {
    }

    public User(long id, String first_name, String last_name, String teleNumb, String email, PersonalAccount personalAccount, InvestingAccount investingAccount) {
        this();
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setTeleNumb(teleNumb);
        setEmail(email);
        setPersonalAccount(personalAccount);
        setInvestingAccount(investingAccount);
    }

    public void transferIN(double amount){
        try{
            this.getPersonalAccount().withdraw(amount);
            this.getInvestingAccount().deposit(amount);
        }
        catch (IllegalArgumentException | IllegalAccessException ex){
            assert true; // tbd
        }
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTeleNumb() {
        return teleNumb;
    }

    public void setTeleNumb(String teleNumb) {
        this.teleNumb = teleNumb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public InvestingAccount getInvestingAccount() {
        return investingAccount;
    }

    public void setInvestingAccount(InvestingAccount investingAccount) {
        this.investingAccount = investingAccount;
    }
}
