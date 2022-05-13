package com.revature.bankapp.daos;

import com.revature.bankapp.model.Users;

import java.io.IOException;

public interface Crudable<U> {
    Users create(Users newUser);

    //@Override
    Users[] findAll() throws IOException;

    Object findById(String id);

    boolean update(Users updatedObj);

    //@Override
    boolean delete(String id);





    static Users authenticateUser(String email, String password) {
        return null;
    }

    boolean checkEmail(String email);
}
