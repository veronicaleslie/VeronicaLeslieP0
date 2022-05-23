package com.revature.bankapp.daos;

import java.io.IOException;
import java.util.List;

public interface Crudable<U> {
    U create(U newObject);
List<U> findAll() throws IOException;
U findById(String id);
public boolean update(U updatedObj);
boolean delete(String id);


    boolean checkEmail(String email);

    void deposit(String value, String id);
}
