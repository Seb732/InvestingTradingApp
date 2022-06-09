package model;

public abstract class Account {
    long balance;

    public Account() {
    }

    public Account(long balance) {
        this();
        setBalance(balance);
    }


    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
