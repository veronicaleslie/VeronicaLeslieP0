package com.revature.bankapp.service;

import com.revature.bankapp.daos.Crudable;
import com.revature.bankapp.daos.UserDao;
import com.revature.bankapp.exceptions.AuthenticationException;
import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.exceptions.ResourcePersistanceException;
import com.revature.bankapp.util.logging.Logger;
import com.revature.bankapp.model.Users;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//@WebServlet("/auth")
public class UserServices implements com.revature.bankapp.service.Serviceable<Users> {

    private final UserDao userDao;
    private final Logger logger = Logger.getLogger();

    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Users[] readAll(){
        logger.info("Begin reading Users in our file database.");


        try {
            // TODO: What trainerDao intellisense telling me?
            Users[] users = userDao.findAll();
            logger.info("All users have been found here are the results: \n");
//
            return users;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users readById(String id){
        return (Users) userDao.findById(id);
    }

    @Override
    public Users update(Users updatedObject) {
        return null;
    }


    @Override
    public boolean delete(String id) {
        return false;
    }

    public boolean validateEmailNotUsed(String email){
        return userDao.checkEmail(email);
    }

    public Users create(Users newUser) throws InvalidRequestException, ResourcePersistanceException {
        logger.info("User trying to be registered: " + newUser);
        if(!validateInput(newUser)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateEmailNotUsed(newUser.getEmail())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        Users persistedUser = userDao.create(newUser);

        if(persistedUser == null){
            throw new ResourcePersistanceException("User was not persisted to the database upon registration");
        }
        logger.info("User has been persisted: " + newUser);
        return persistedUser;
    }

    public boolean validateInput(Users newUser) {
        logger.debug("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getFirst_name() == null || newUser.getFirst_name().trim().equals("")) return false;
        if(newUser.getLast_name() == null || newUser.getLast_name().trim().equals("")) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        return newUser.getUsername() != null || !newUser.getUsername().trim().equals("");
    }

    public Users authenticateUser(String email, String password) throws InvalidRequestException, AuthenticationException {

        if(password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Users authenticatedUser = Crudable.authenticateUser(email, password);

        if (authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedUser;

    }
}