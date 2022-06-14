package com.revature.pokedex; // determining the package structure for the output

// importing the class from other packages to be leveraged
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.models.Trainer;
import com.revature.pokedex.util.AppState;

// importing everything from java.io.
// this is a library that's provided by....JRE
import java.io.*;

// What's this?
// Creating a class called MainDriver
public class MainDriver {

    public static void main(String[] args){

        System.out.println("1. AppState instantiated");
        AppState appState = new AppState();

        System.out.println("Pokedex Application starting up....");
        appState.startup();

    }
}


