package com.revature.bankapp.daos;


import com.revature.bankapp.model.Account;

public class AccountDao {
    public void deposit(String value, String id) {
    }

    public void withdraw(String value, String id) {
    }

    public com.revature.bankapp.model.Account create(com.revature.bankapp.model.Account newAccount) {
        return newAccount;
    }

    public com.revature.bankapp.model.Account[] findAll(String email) {
        return new Account[0];
    }

    public com.revature.bankapp.model.Account findById(String id) {
        return null;
    }

    public Account readAccount(String email) {
        return null;
    }
}
