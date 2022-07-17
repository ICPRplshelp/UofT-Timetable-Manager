package org.example.logincode.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Presenter {


    // Collection<String> loggedOutPrompt = List.of("PLEASE LOG IN");
    // Will remove the following prompts after merge
    Collection<String> loggedInStandardPrompt = List.of(new String[]{
            "Enter 'history' to see your login history",
            "Enter 'adminview' to enter the admin view if you are an admin",
            "Enter 'setpassword' to change your password",
            "Enter 'secretadmin' to make yourself an admin"});
    Collection<String> loggedInAdminOnlyPrompt = List.of(new String[]{
            "Enter 'ban' to temporarily ban a user",
            "Enter 'delete' to delete a user",
            "Enter 'new' to create a new AdminUser",
            "Enter 'promote' to promote an existing User to Admin",
            "Enter 'back' to return to the standard user prompt"
    });

    Map<String, String> userPrompts = Map.of(
            "history", "see your login history",
            "adminview", "enter the admin view if you are an admin",
            "setpassword", "change your password",
            "secretadmin", "make yourself an admin",
            "ban", "make yourself an admin",
            "delete", "temporarily ban a user",
            "new", "delete a user",
            "promote", "promote an existing User to Admin",
            "back", "return to the standard user prompt"
    );
    Scanner scanner = new Scanner(System.in);
    PresenterPrinter prt = new PresenterPrinter();


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
                return dashboardView(List.of("I would like to list some commands but I cant"));
            }
        }
    }

    public String printAndAskPrompt(String[] commandsList) {
        return dashboardView(inputPromptHelper(commandsList));
    }

    public Collection<String> inputPromptHelper(String[] commandsList) {
        try{    Collection<String> returnCollection = new ArrayList<String>();
            for(String item: commandsList) {
                returnCollection.add("Enter '" + item + "' " +
                        Objects.requireNonNullElse(userPrompts.get(item), "idk"));
            }
            return returnCollection;
        } catch (NullPointerException e){
            return List.of("We couldn't come up with a command list.");
        }

    }

    public void startView() {
        prt.println("Enter 'register' to register, 'login' to login, or 'exit' to exit: ");
    }

    /**
     * Forces the user to enter the credentials. Return their input.
     *
     * @return A string array: [username, password].
     */
    public String[] enterCredentials() {
        // register and login both use this method since their procedures are identical
        String[] inputs = new String[2];

        prt.println("Enter Username: ");
        inputs[0] = scanner.nextLine();

        prt.println("Enter Password: ");
        inputs[1] = scanner.nextLine();

        return inputs;
    }

    public String enterUsername() {
        prt.println("Enter username of target user: ");
        return scanner.nextLine();
    }

    public Date enterDate() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        prt.println("Enter date in the format dd/MM/yyyy: ");
        String input = prt.askWithMessage("Enter date in the format dd/MM/yyyy: ");
        return dateFormatter.parse(input);
    }


    public void registerConfirm(boolean isSuccessful) {
        if (isSuccessful) {
            prt.println("Account successfully created.");
        } else {
            prt.println("Username already taken. Please choose another username");
        }
    }

    public void loginConfirm(boolean isSuccessful) {
        if (isSuccessful) {
            prt.println("You have successfully logged in");
        } else {
            prt.println("Incorrect username or password");
        }
    }

    public String[] passwordChangePrompt() {
        String[] inputs = new String[2];
        inputs[0] = prt.askWithMessage("Old password: ");
        inputs[1] = prt.askWithMessage("New password: ");
        return inputs;
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


    public void banUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully banned " + username);
        } else {
            prt.println("You do not have permission, the user does not exist, you tried to ban an admin, or you tried to ban yourself.");
        }
    }

    public void deleteUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully deleted " + username);
        } else {
            prt.println("You don't have permission, the user does not exist, the user has perms that prevents it from being deleted, or you tried to delete yourself.");
        }
    }

    public void createNewAdminConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully created a new Admin " + username);
        } else {
            prt.println("You do not have permission or the user already exists");
        }
    }

    public void promoteUserConfirm(boolean isSuccessful, String username) {
        if (isSuccessful) {
            prt.println("You have successfully promoted " + username);
        } else {
            prt.println("You do not have permission or the user does not exist");
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

    public String enterCourse() {
        prt.println("Enter course code: ");
        return scanner.nextLine();
    }

    public String enterSession() {
        prt.println("Enter course session: ");
        return scanner.nextLine();
    }

    public void printCourseSessionsByType(String type, Collection<String> sessions) {
        prt.println(type);
        for (String s : sessions) {
            prt.println(s);
        }
        prt.println("");    // spacer
    }

    public void parseFailure() {
        prt.println("Failed to parse string to date");
    }

    public void printHistory(String history) {
        prt.println(history);
    }

    public void genericError() {
        prt.println("An error has occurred.");
    }

    public void exitProgram() {
        prt.println("You have exited the program.");
    }


}
