package com.revature.bankapp.daos;


import com.revature.bankapp.model.Account;

public class AccountDao {
    public static boolean update(String id2, String newAccount) {
        return false;
    }

    public static Account deposit(String value, String id) {
        return null;
    }

    public static Account withdraw(String value, String id) {
        return null;
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
