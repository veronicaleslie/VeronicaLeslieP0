package com.revature.bankapp.service;

import com.revature.bankapp.model.Users;

;


public interface Serviceable<U> {
    Users[] readAll();

    Users readById(String id);

    Users update(Users updatedObject);

    U update(U updatedObject);

    boolean delete(String id);
}
