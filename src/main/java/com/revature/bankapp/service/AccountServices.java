package com.revature.bankapp.service;

import com.revature.bankapp.daos.AccountDao;
import com.revature.bankapp.exceptions.ResourcePersistanceException;
import com.revature.bankapp.model.Account;
import com.revature.bankapp.model.Users;
import com.revature.bankapp.util.logging.Logger;

import java.io.IOException;

public class AccountServices {
    private AccountDao accountDao = new AccountDao();

    public AccountServices(com.revature.bankapp.daos.AccountDao accountDao) {
    }

    public Account[] readAccount(String email) {
       Account[] accounts = new Account[0];
        try {
            accounts = accountDao.findAll(email);
            for (int i = 0; i < accounts.length; i++) {
                Account account = accounts[i];
                System.out.println(account.toString());
            }
        } catch (NullPointerException e) {
            // e.printStackTrace();
        }
        return accounts;
    }
    public void deposit(String value, String id){
        accountDao.deposit(value, id);
    }
    public void withdraw(String value, String id){

        accountDao.withdraw(value, id);
    }

    public boolean registerAccount(Account newAccount){
        if(!validateAccountInput(newAccount)){ // checking if false
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateAccountInput(newAccount);

        Account persistedAccount = accountDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("Account has been registered: " + newAccount);
        return true;
    }
    private boolean validateAccountInput(Account newAccount) {
        if(newAccount == null) return false;
        if(newAccount.getAccountID() == 0) return false;
        if(newAccount.getAccountName() == null || newAccount.getAccountName().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");
    }
    public Account readAccountById(String id) {
        Logger logger = null;
        Account account = new Account();
        try {
            account = accountDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    public Object readAccounts(String email) {
        Logger logger = null;
        Account account = new Account();
        try {
            account = accountDao.readAccount(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
