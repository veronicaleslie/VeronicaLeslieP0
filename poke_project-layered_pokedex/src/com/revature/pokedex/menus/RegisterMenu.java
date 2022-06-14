package com.revature.pokedex.menus;

import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.services.TrainerServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterMenu extends Menu{

    private TrainerServices trainerServices = new TrainerServices();

    public RegisterMenu(BufferedReader terminalReader) {
        super("Register", "/register", terminalReader);
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
        String fname = nameArray[0];
        String lname = nameArray[1];

        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new Trainer object in memory
        Trainer newTrainer = new Trainer(fname, lname, email, password, dob);
        System.out.println("Here is the trainer that was provided by the user: " + newTrainer);
        trainerServices.registerTrainer(newTrainer);
    }
}
