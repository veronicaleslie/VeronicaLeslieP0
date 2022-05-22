package com.revature.bankapp.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.model.Account;
import com.revature.bankapp.service.AccountServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AccountServlet extends HttpServlet {
    private final AccountServices accountServices;
    private final ObjectMapper mapper;

    public AccountServlet(AccountServices accountServices, ObjectMapper mapper) {
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuth(req, resp)) return;

        if (req.getParameter("id") != null) {
            Account account = accountServices.readAccountById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING

            String payload = mapper.writeValueAsString(account);
            resp.getWriter().write(payload);
            resp.setStatus(201);
            return;
        }

         List<Account> accounts = Arrays.asList(accountServices.readAccount(req.getParameter("id")));
        //List<Account> accounts = Arrays.asList(accountServices.readAccount(req.getParameter("id")));
        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkAuth(req, resp)) return;

        Account newAccount = mapper.readValue(req.getInputStream(), Account.class);
        Account persistedAccount = accountServices.create(newAccount);
        String payload = mapper.writeValueAsString(persistedAccount);

        resp.getWriter().write("Account persisted");
            resp.getWriter().write(payload);
            resp.setStatus(201);
        }



    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("authUser") == null) {
            resp.getWriter().write("Unauthorized request - not logged in ");
            resp.setStatus(401);
            return true;
        }
        return false;

    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
