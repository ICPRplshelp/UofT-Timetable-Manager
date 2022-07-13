package org.example.logincode.interfaceadapters;

import org.example.logincode.interfaceadapters.controllerInput.LoggedInState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Presenter {


    // Collection<String> loggedOutPrompt = List.of("PLEASE LOG IN");
    Collection<String> loggedInStandardPrompt = List.of(new String[]{
            "Enter 'history' to see your login history",
            "Enter 'adminview' to enter the admin view if you are an admin",
            "Enter 'setpassword' to change your password",
            "Enter 'secretadmin' to make yourself an admin"});
    Collection<String> loggedInAdminOnlyPrompt = List.of(new String[]{
            "Enter 'history' to see your login history",
            "Enter 'ban' to temporarily ban a user",
            "Enter 'delete' to delete a user",
            "Enter 'new' to create a new AdminUser",
            "Enter 'promote' to promote an existing User to Admin",
            "Enter 'back' to return to the standard user prompt"
    });
    Scanner scanner = new Scanner(System.in);


    public Presenter() {
    }

    public String printAndAskPrompt(LoggedInState cState) {
        switch (cState) {
            case STANDARD -> {
                return dashboardView(loggedInStandardPrompt);
            }
            case ADMIN -> {
                return dashboardView(loggedInAdminOnlyPrompt);
            }
            default -> {
                System.out.println("Invalid input");
                return "AAA";
            }
        }
    }

    public void startView() {
        System.out.println("Enter 'register' to register, 'login' to login, or 'exit' to exit: ");
    }

    /**
     * Forces the user to enter the credentials. Return their input.
     *
     * @return A string array: [username, password].
     */
    public String[] enterCredentials() {
        // register and login both use this method since their procedures are identical
        String[] inputs = new String[2];

        System.out.println("Enter Username: ");
        inputs[0] = scanner.nextLine();

        System.out.println("Enter Password: ");
        inputs[1] = scanner.nextLine();

        return inputs;
    }

    public String enterUsername() {
        System.out.println("Enter username of target user: ");
        return scanner.nextLine();
    }

    public Date enterDate() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        System.out.println("Enter date in the format dd/MM/yyyy: ");
        String input = scanner.nextLine();
        return dateFormatter.parse(input);
    }


    public void registerConfirm(boolean isSuccessful) {
        if (isSuccessful) {
            System.out.println("Account successfully created.");
        } else {
            System.out.println("Username already taken. Please choose another username");
        }
    }

    public void loginConfirm(boolean isSuccessful) {
        if (isSuccessful) {
            System.out.println("You have successfully logged in");
        } else {
            System.out.println("Incorrect username or password");
        }
    }

    public String[] passwordChangePrompt() {
        String[] inputs = new String[2];

        System.out.println("Old password: ");
        inputs[0] = scanner.nextLine();

        System.out.println("New password: ");
        inputs[1] = scanner.nextLine();

        return inputs;
    }

    /**
     * Prints all elements in messages and returns what the user sent.
     *
     * @param messages a collection of messages to send to the user.
     * @return the user input.
     */
    public String dashboardView(Collection<String> messages) {
        messages.forEach(System.out::println);
        return scanner.nextLine();
    }


    public void banUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            System.out.println("You have successfully banned " + username);
        } else {
            System.out.println("You do not have permission, the user does not exist, you tried to ban an admin, or you tried to ban yourself.");
        }
    }

    public void deleteUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            System.out.println("You have successfully deleted " + username);
        } else {
            System.out.println("You don't have permission, the user does not exist, the user has perms that prevents it from being deleted, or you tried to delete yourself.");
        }
    }

    public void createNewAdminConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            System.out.println("You have successfully created a new Admin " + username);
        } else {
            System.out.println("You do not have permission or the user already exists");
        }
    }

    public void promoteUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            System.out.println("You have successfully promoted " + username);
        } else {
            System.out.println("You do not have permission or the user does not exist");
        }
    }

    public void genericFailedAction(String reason) {
        switch (reason) {
            case "invalid" -> System.out.println("Invalid input.");
            case "noPerms" -> System.out.println("You do not have the admin permission.");
            case "invalidPassword" ->
                    System.out.println("The password you were asked to enter that is to be checked was incorrect");
            default -> System.out.println("Action failed.");
        }
    }

    public void parseFailure() {
        System.out.println("Failed to parse string to date");
    }

    public void printHistory(String history) {
        System.out.println(history);
    }

    public void genericError() {
        System.out.println("An error has occurred.");
    }

    public void exitProgram() {
        System.out.println("You have exited the program.");
    }


}
