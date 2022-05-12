package com.revature.bankapp.services;

import com.revature.bankapp.models.Users;


public interface Serviceable<U> {
    Users readById(String id);

    Users update(Users updatedObject);

    boolean delete(String id);
}
