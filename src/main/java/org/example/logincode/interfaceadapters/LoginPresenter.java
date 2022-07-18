package org.example.logincode.interfaceadapters;

import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.util.*;

public class LoginPresenter extends Presenter{

    Map<String, String> userPrompt = Map.of(
            "history", "see your login history",
            "adminview", "enter the admin view if you are an admin",
            "setpassword", "change your password",
            "secretadmin", "make yourself an admin",
            "ban", "make yourself an admin",
            "delete", "temporarily ban a user",
            "new", "delete a user",
            "promote", "promote an existing User to Admin",
            "ttview", "enter the timetable view",
            "back", "return to the standard user prompt"
    );

    public LoginPresenter() {
    }

    public String printAndAskPrompt(String[] commandsList) {
        return dashboardView(inputPromptHelper(commandsList, userPrompt));
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

    public void exitProgram() {
        prt.println("You have exited the program.");
    }


}
