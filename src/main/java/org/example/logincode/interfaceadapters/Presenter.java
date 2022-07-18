package org.example.logincode.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Presenter {
    protected Scanner scanner = new Scanner(System.in);
    protected PresenterPrinter prt = new PresenterPrinter();

    public void parseFailure() {
        prt.println("Failed to parse string to date");
    }
    public void genericError() {
        prt.println("An error has occurred.");
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

    public Collection<String> inputPromptHelper(String[] commandsList, Map<String, String> userPrompt) {
        if(Objects.isNull(commandsList)) return List.of("No command string?");
        try{    Collection<String> returnCollection = new ArrayList<String>();
            for(String item: commandsList) {
                returnCollection.add("Enter '" + item + "' " +
                        Objects.requireNonNullElse(userPrompt.get(item), "idk"));
            }
            return returnCollection;
        } catch (NullPointerException e){
            return List.of("We couldn't come up with a command list.");
        }
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
    /**
     * Prints all elements in messages and returns what the user sent.
     *
     * @param messages a collection of messages to send to the user.
     * @return the user input.
     */
    public String dashboardView(Collection<String> messages) {
        return prt.askWithMessage(String.join("\n", messages));
    }
}

