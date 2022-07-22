package org.example.logincode.interfaceadapters.presenters;

import org.example.PresenterPrinter;

import java.util.Scanner;

public class Presenter {
    protected Scanner scanner = new Scanner(System.in);
    protected PresenterPrinter prt = new PresenterPrinter();

    public void parseFailure() {
        prt.println("Failed to parse string to date");
    }


    public String[] enterCredentials() {
        // register and login both use this method since their procedures are identical
        String[] inputs = new String[2];

        prt.println("Enter Username: ");
        inputs[0] = scanner.nextLine();

        prt.println("Enter Password: ");
        inputs[1] = scanner.nextLine();

        return inputs;
    }

    public void genericFailedAction(String reason) {
        switch (reason) {
            case "invalid" -> prt.println("Invalid input.");
            case "noPerms" -> prt.println("You do not have the admin permission.");
            case "invalidPassword" ->
                    prt.println("The password you were asked to enter that is to be checked was incorrect");
            default -> prt.println("Action failed.");
        }
    }
}
