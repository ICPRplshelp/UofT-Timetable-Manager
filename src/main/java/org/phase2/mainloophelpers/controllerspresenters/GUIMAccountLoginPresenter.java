package org.phase2.mainloophelpers.controllerspresenters;

import javax.swing.*;

public class GUIMAccountLoginPresenter {

    JLabel error;

    public GUIMAccountLoginPresenter(JLabel error) {
        this.error = error;
    }


    public void registerState(boolean success) {
        if (success) {
            System.out.println("Register successful.");
            error.setText("");
        }
        else error.setText("Username already exists, or username/password was blank");
    }

    public void loginState(boolean success) {
        if (success) {
            System.out.println("Logged in.");
            error.setText("");
        }
        else error.setText("Account DNE or password incorrect.");
    }


}