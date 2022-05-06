package com.revature.bankapp.menu;


import com.revature.bankapp.models.Users;
import com.revature.bankapp.service.UserServices;
import models.Users;
import models.UsersBuilder;

import java.io.BufferedReader;

public class RegisterMenu extends Menu{

    private UserServices userServices = new UserServices();

    public RegisterMenu(BufferedReader terminalReader) {
        super("Sign Up", "/Sign Up", terminalReader);
    }

    @Override
    public void render() throws Exception {
        // TODO: Implement me!!!
        System.out.println("What is your full name?");
        String fullName = terminalReader.readLine();

        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("DOB?");
        String dob = terminalReader.readLine();

        // What's happening here???
        // Breaking or splitting the String fullName into an String array by " " spaces
        String[] nameArray = fullName.split(" ");
        String first_name = nameArray[0];
        String last_name = nameArray[1];

        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new Trainer object in memory
        Object last_name = new Object();
        Object first_name = new Object();
        String user_name = "";
        Users newTrainer = new UsersBuilder().setFirst_name(first_name).setLast_name(last_name).setEmail(user_name).setPassword(password).createUsers();
        System.out.println("Here is the trainer that was provided by the user: " + newTrainer);
        UserServices.registerUser(new  User);
    }
}

public class RegistrationMenu {
}
