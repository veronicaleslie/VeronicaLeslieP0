package com.revature.bankapp.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bankapp.exceptions.AuthenticationException;
import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.model.Users;
import com.revature.bankapp.service.UserServices;
import com.revature.bankapp.web.dto.LoginCreds;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/auth")


public class AuthServlet extends HttpServlet {

    private final UserServices userServices;
    // ObjectMapper provided by jackson
    private final ObjectMapper mapper;

    public AuthServlet(UserServices userServices, ObjectMapper mapper){
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Users authUser = userServices.authenticateUser(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);

            resp.getWriter().write("You have successfully logged in!");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }
    }

}
