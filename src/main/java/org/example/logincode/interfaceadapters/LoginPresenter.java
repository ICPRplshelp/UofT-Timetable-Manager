package org.example.logincode.interfaceadapters;

import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.util.*;

public class LoginPresenter extends Presenter{


    // Collection<String> loggedOutPrompt = List.of("PLEASE LOG IN");
    // Will remove the following prompts after merge
    Collection<String> loggedInStandardPrompt = List.of(new String[]{
            "Enter 'history' to see your login history",
            "Enter 'adminview' to enter the admin view if you are an admin",
            "Enter 'setpassword' to change your password",
            "Enter 'secretadmin' to make yourself an admin",
            "Enter 'ttview' to enter the timetable view"
    });
    Collection<String> loggedInAdminOnlyPrompt = List.of(new String[]{
            "Enter 'ban' to temporarily ban a user",
            "Enter 'delete' to delete a user",
            "Enter 'new' to create a new AdminUser",
            "Enter 'promote' to promote an existing User to Admin",
            "Enter 'back' to return to the standard user prompt"
    });

    Collection<String> timetablePrompt = List.of(new String[]{
            "Enter 'view' to view the current timetable",
            "Enter 'addcourse' to add a course",
            "Enter 'addmeetingtocourse' to add a lecture time to a course",
            "Enter 'addprevcourse' to add a previously taken course",
            "Enter 'delcourse' to delete a current course",
            "Enter 'delprevcourse' to delete a previously taken course",
            "Enter 'back' to return to the standard user prompt"
    });

    Map<String, String> userPrompt = Map.of(
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

    public LoginPresenter() {
    }

    public String printAndAskPrompt(LoggedInState cState) {
        switch (cState) {
            case STANDARD -> {
                return dashboardView(loggedInStandardPrompt);
            }
            case ADMIN -> {
                return dashboardView(loggedInAdminOnlyPrompt);
            }
            case TIMETABLE -> {
                return dashboardView(timetablePrompt);
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

    public void startView() {
        prt.println("Enter 'register' to register, 'login' to login, or 'exit' to exit: ");
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

    /**
     * Prints all elements in messages and returns what the user sent.
     *
     * @param messages a collection of messages to send to the user.
     * @return the user input.
     */
    public String dashboardView(Collection<String> messages) {
        return prt.askWithMessage(String.join("\n", messages));
    }












    public void exitProgram() {
        prt.println("You have exited the program.");
    }


}
