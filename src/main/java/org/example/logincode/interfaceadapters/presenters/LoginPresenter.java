package org.example.logincode.interfaceadapters.presenters;

import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.util.*;

public class LoginPresenter extends Presenter {


    final Map<String, String> userPrompt = Map.ofEntries(

            // Standard user actions
            Map.entry("history", "see your login history"),
            Map.entry("adminview", "enter the admin view if you are an admin"),
            Map.entry("coursesearch", "enter course search view"),
            Map.entry("ttview", "see your timetable"),
            Map.entry("setpassword", "change your password"),
            Map.entry("secretadmin", "make yourself an admin"),

            // Admin actions
            Map.entry("ban", "temporarily ban a user"),
            Map.entry("delete", "delete a user"),
            Map.entry("new", "make a new Admin"),
            Map.entry("promote", "promote an existing User to Admin"),

            // Course search actions
            Map.entry("search", "search up courses by keyword"),
            Map.entry("pastcourses", "search up courses from past sessions by keyword"),
            Map.entry("courseinfo", "search up info about a course"),
            Map.entry("sections", "see LEC/TUT/PRA sections of a course"),

            // Timetable actions
            Map.entry("view", "view the current timetable"),
            Map.entry("viewprevcourses", "view previous courses taken"),
            Map.entry("addcourse", "add a course"),
            Map.entry("addmeetingtocourse", "add a lecture time to a course"),
            Map.entry("addprevcourse", "add a previously taken course"),
            Map.entry("delcourse", "delete a current course"),
            Map.entry("delprevcourse", "delete a previously taken course"),

            Map.entry("back", "return to the standard user prompt")
    );

    public LoginPresenter() {
    }

    public String printAndAskPrompt(String[] commandsList) {
        return dashboardView(inputPromptHelper(commandsList));
    }

    public Collection<String> inputPromptHelper(String[] commandsList) {
        if (Objects.isNull(commandsList)) return List.of("No command string?");
        try {
            Collection<String> returnCollection = new ArrayList<>();
            for (String item : commandsList) {
                returnCollection.add("Enter '" + item + "' to " +
                        Objects.requireNonNullElse(userPrompt.get(item), "idk"));
            }
            return returnCollection;
        } catch (NullPointerException e) {
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
