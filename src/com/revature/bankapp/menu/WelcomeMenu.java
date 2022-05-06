package com.revature.bankapp.menu;

import com.revature.bankapp.menu.Menu;
import com.revature.bankapp.service.UserServices;

import java.io.BufferedReader;

public class WelcomeMenu extends Menu {

    private UserServices userServicesServices;

    public WelcomeMenu(BufferedReader terminalReader, UserServices userServices) {
        super("Welcome", "/welcome", terminalReader);
        this.userServices = userServices;
    }

    @Override
    public void render() throws Exception {
        String welcome = "Welcome to your banking app!";
        String option1 = "1) Login";
        String option2 = "2) Sign up";
        String option3 = "3) Exit your banking app";


        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3).println();

        System.out.print("\n Select number from above\n >");
        String userSelection = terminalReader.readLine();

        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected sign up...");
                // register(); // ctrl + left-click
                break;
            case "3":
                System.out.println("User has selected exit...");
                // shutdown application here
                shutdown();
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}

