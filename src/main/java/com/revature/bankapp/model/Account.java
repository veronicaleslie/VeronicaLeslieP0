package com.revature.bankapp.model;

public class Account {
    private String username;
    private String accountName;
    private int accountID;
    private int balance;


    public Account(String username, String accountName, int accountID) {
        super();
        this.username= username;
        this.accountName = accountName;
        this.balance = 0;
        this.accountID = accountID;
    }

    public Account() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountID() {
        return accountID;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "Username: " + username + '\'' +
                        "| Accountname: " + accountName + '\'' +
                        "| AccountID='" + accountID + '\'' +
                        "| Balance='" + balance + '\'';
    }



}
