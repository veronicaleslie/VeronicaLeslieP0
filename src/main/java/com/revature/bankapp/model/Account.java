package com.revature.bankapp.model;

public class Account {
    private String email;
    private String accountName;
    private int accountID;
    private int balance;


    public Account(String email, String accountName, int accountID) {
        super();
        this.email = email;
        this.accountName = accountName;
        this.balance = 0;
        this.accountID = accountID;
    }
    public Account(){

    }

    public static int getCurrentAccountAmount() {
        return 0;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getAccountID() {
        return accountID;
    }
    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", accountname='" + accountName + '\'' +
                ", accountID='" + accountID + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
