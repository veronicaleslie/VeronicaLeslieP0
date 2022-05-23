package com.revature.bankapp.service;

import com.revature.bankapp.daos.AccountDao;
import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.model.Account;

import java.io.IOException;

public class AccountServices {
    private final AccountDao accountDao = new AccountDao();


    public Account[] readAccount(String email) throws IOException {
        Account account[]; account = new Account[0];
        try {
            account = accountDao.findAll(email);
            for (int i = 0; i < account.length; i++) {
                Account accounts = account[i];
                System.out.println(account.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } {
        }
        return account;
    }
    public void deposit(String value, String id) throws InvalidRequestException {
        accountDao.deposit(value, id);
    }

    public boolean registerAccount(Account newAccount){
        if(!validateAccountInput(newAccount)){
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateAccountInput(newAccount);

        Account persistedAccount = accountDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("THE ACCOUNT HAS BEEN REGISTERED TO OUR SYSTEM:  " + newAccount);
        return true;
    }
    private boolean validateAccountInput(Account newAccount) {
        if(newAccount == null) return false;
        if(newAccount.getAccountID() == 0) return false;
        if(newAccount.getAccountName() == null || newAccount.getAccountName().trim().equals("")) return false;
        return newAccount.getUsername() != null || !newAccount.getUsername().trim().equals("");
    }
    public Account readAccountById(String id) {
        Account account = newAccount();
        try {
            account = accountDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    private Account newAccount() {
        return null;
    }


}

