package com.revature.bankapp.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bankapp.daos.AccountDao;
import com.revature.bankapp.daos.UserDao;
import com.revature.bankapp.service.AccountServices;
import com.revature.bankapp.service.UserServices;
import com.revature.bankapp.web.servlets.AccountServlet;
import com.revature.bankapp.web.servlets.AuthServlet;
import com.revature.bankapp.web.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();

        // Instantiate and intialize the services with their dao dependency
        UserServices userServices = new UserServices(userDao);
        AccountServices accountServices = new AccountServices(accountDao);


        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AccountServlet", accountServlet).addMapping("/account/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
