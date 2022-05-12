package com.revature.bankapp.service;

import com.revature.bankapp.model.Users;

;


public interface Serviceable<U> {
    Users readById(String id);

    Users update(Users updatedObject);

    boolean delete(String id);
}
