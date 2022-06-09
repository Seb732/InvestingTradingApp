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

    public void deposit(double amount){
        if (amount > 1){
            this.balance += amount;
        }
    }
    public void withdraw(double amount) throws IllegalAccessException {
        if (amount <= this.balance){
            this.balance -= amount;
        }
        else{
            throw new IllegalAccessException();
        }
    }
}
