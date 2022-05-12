package main.java.bankapp.services;

import main.java.com.revature.bankapp.daos.models.Users;


public interface Serviceable<U> {
    Users readById(String id);

    Users update(Users updatedObject);

    boolean delete(String id);
}
