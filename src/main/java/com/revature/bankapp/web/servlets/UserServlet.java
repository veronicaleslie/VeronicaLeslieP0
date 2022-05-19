package com.revature.bankapp.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bankapp.exceptions.ResourcePersistanceException;
import com.revature.bankapp.model.Users;
import com.revature.bankapp.service.UserServices;
import com.revature.bankapp.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserServices userServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    //@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!Authable.checkAuth(req, resp)) return;
        // The below code allows to split information from the endpoint after the /trainers/. Reminder the first element is empty because it takes the value from before the first /
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        // Handling the query params in the
        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("You entered the following id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        // Handling the query params in the endpoint /trainers?id=x
        if(req.getParameter("id") != null){
            Users user;
            try {
                user = userServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (Exception e) {
                throw new RuntimeException(e);
                //resp.setStatus(404);
            }


            String payload = mapper.writeValueAsString(user);
            resp.getWriter().write(payload);
            return;
        }

        List<Users> users = Arrays.asList(userServices.readAll());
        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);
    }

    //@Override
   // public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

