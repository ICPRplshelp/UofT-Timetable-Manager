package org.phase2.mainloophelpers.controllerspresenters;

public class MAccountLoginPresenter {

    public void registerState(boolean success) {
        if (success) System.out.println("Register successful.");
        else System.out.println("Username already exists, or username/password was blank");
    }

    public void loginState(boolean success) {
        if (success) System.out.println("Logged in.");
        else System.out.println("Account DNE or password incorrect.");
    }

    public void getRegCommands() {
        System.out.println("""
                Welcome to the login page!
                Commands:
                /register <username> <password>
                /login <username> <password>""");
    }


}
