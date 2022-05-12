package main.java.com.revature.bankapp.daos.services;

import main.java.com.revature.bankapp.daos.daos.UserDao;
import main.java.bankapp.exceptions.AuthenticationException;
import main.java.bankapp.exceptions.InvalidRequestException;
import main.java.bankapp.exceptions.ResourcePersistanceException;
import main.java.bankapp.util.logging.Logger;
import main.java.models.Users;
import serviceable.==

public class UserServices<Users> implements Serviceable<Users>{

    private UserDao userDao;
    private Logger logger = Logger.getLogger();

    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Users readById(String id){
        Users users = userDao.findById(id);
        return users;
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
        return UserDao.checkEmail(email);
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

    public Users authenticateTrainer(String email, String password) throws InvalidRequestException, AuthenticationException {

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        Users authenticatedUser = userDao.authenticateUser(email, password);

        if (authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedUser;

    }
}