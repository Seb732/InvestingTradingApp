package model;

public abstract class Account {
    double balance;

    public Account() {
    }

    public Account(double balance) {
        this();
        setBalance(balance);
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
