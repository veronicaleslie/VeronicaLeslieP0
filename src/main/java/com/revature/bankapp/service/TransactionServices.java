package com.revature.bankapp.service;

import com.revature.bankapp.daos.AccountDao;
import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.model.Account;

import javax.servlet.annotation.WebServlet;

@WebServlet("/transaction")
public class TransactionServices extends AccountServices {
    public TransactionServices(AccountDao accountDao) {
        super(accountDao);
    }
    public Object deposit(String deposit, String id) throws InvalidRequestException {

        if (deposit(deposit) == false) {
            throw new InvalidRequestException("Invalid Amount: Amount must exceed 0");
        }


        return null;
    }

    private boolean deposit(String deposit) {
        return false;
    }

    public Account withdraw(String deposit, String id) throws InvalidRequestException {


        if (withdrawAmount(deposit) == false) {
            throw new InvalidRequestException("Invalid Amount: Amount must exceed 0 or Amount cannot exceed balance");
        }

        Account withdrawAmount = AccountDao.withdraw(deposit, id);

        //

        return withdrawAmount;

    }

    public boolean withdrawAmount(String deposit) {

        if (Integer.parseInt(deposit) < 0 || deposit.equals("")) return false;

        if (Account.getCurrentAccountAmount()- Integer.parseInt(deposit) < 0) return false;

        return true;
    }
}
