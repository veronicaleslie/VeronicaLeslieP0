package com.revature.pokedex.services;

import com.revature.pokedex.daos.TrainerDao;
import com.revature.pokedex.models.Trainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrainerServices {

    private TrainerDao trainerDao = new TrainerDao();

    public void readTrainers(){
        System.out.println("Begin reading Trainers in our file database.");
        Trainer[] trainers = new Trainer[0];
        try {
            trainers = trainerDao.findAll();
            System.out.println("All trainers have been found here are the results: \n");
            for (int i = 0; i < trainers.length; i++) {
                Trainer trainer = trainers[i];
                System.out.println(trainer.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
    }

    // TODO: Implement me to check that the email is not already in our database.
    public boolean validateEmailNotUsed(String email){
        trainerDao.checkEmail(email);
        return false;
    }
    
    public boolean registerTrainer(Trainer newTrainer){
        System.out.println("Trainer trying to be registered: " + newTrainer);
        if(!validateTrainerInput(newTrainer)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newTrainer.getEmail());

        Trainer persistedTrainer = trainerDao.create(newTrainer);

        if(persistedTrainer == null){
            throw new RuntimeException();
        }
        System.out.println("Trainer has been persisted: " + newTrainer);
        return true;
    }

    private boolean validateTrainerInput(Trainer newTrainer) {
        System.out.println("Validating Trainer: " + newTrainer);
        if(newTrainer == null) return false;
        if(newTrainer.getFname() == null || newTrainer.getFname().trim().equals("")) return false;
        if(newTrainer.getLname() == null || newTrainer.getLname().trim().equals("")) return false;
        if(newTrainer.getEmail() == null || newTrainer.getEmail().trim().equals("")) return false;
        if(newTrainer.getPassword() == null || newTrainer.getPassword().trim().equals("")) return false;
        return newTrainer.getDob() != null || !newTrainer.getDob().trim().equals("");
    }
}
