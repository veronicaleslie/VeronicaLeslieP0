package com.revature.bankapp.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bankapp.daos.AccountDao;
import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.model.Account;
import com.revature.bankapp.service.TransactionServices;
import com.revature.bankapp.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

public class TransactionServlet extends HttpServlet {
    private TransactionServices transactionServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public TransactionServlet(TransactionServices transactionService, ObjectMapper mapper) throws ServletException, IOException {
        this.transactionServices = transactionServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Authable.checkAuth(req, resp)) System.out.println("user not authorized");


        if (req.getParameter("deposit") != null) {
            try {
//using payload to send info needed from account, in this case how muxh to withdraw/deposit
                String payload = mapper.writeValueAsString(transactionServices.deposit(req.getParameter("deposit"), req.getParameter("username")));
                resp.getWriter().write(payload);

            } catch (InvalidRequestException e) {
                throw new RuntimeException(e);
            }
            class BankDetails {
                private String username;
                private String acc_type;
                private long balance;
                Scanner sc = new Scanner(System.in);


                public void openAccount() {
                    System.out.print("Enter Account type: ");
                    acc_type = sc.next();
                    System.out.print("Enter Username: ");
                    username = sc.next();
                    System.out.print("Enter Balance: ");
                    balance = sc.nextLong();
                }

                public void showAccount() {
                    System.out.println("Name of account holder: " + username);
                    System.out.println("Balance: " + balance);
                }

                public void deposit() {
                    long amt;
                    System.out.println("Enter deposit amount: ");
                    amt = sc.nextLong();
                    balance = balance + amt;
                }

                public void withdrawal() {
                    long amt;
                    System.out.println("Enter the amount you want to withdraw: ");
                    amt = sc.nextLong();
                    if (balance >= amt) {
                        balance = balance - amt;
                        System.out.println("Balance after withdrawal: " + balance);
                    } else {
                        System.out.println("Withdrawal cannot exceed balance " + amt + "\tTransaction failed...!!");
                        if (amt <= 0) {
                            System.out.println("Amount must exceed 0");
                        }
                    }

                }
            }
        }

    }
}