package com.revature.bankapp.service;

import com.revature.bankapp.exceptions.InvalidRequestException;
import com.revature.bankapp.exceptions.ResourcePersistanceException;
import com.revature.bankapp.model.Users;

;import java.util.List;


public interface Serviceable<U> {
    U create(U newObject) throws InvalidRequestException, ResourcePersistanceException;
    List<U> readAll();

    U readById(String id) throws ResourcePersistanceException;

    U update(Users updatedObject);


    boolean delete(String id);
    boolean validateInput(U object);
}
