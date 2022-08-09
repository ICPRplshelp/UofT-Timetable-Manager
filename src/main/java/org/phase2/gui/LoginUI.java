package org.phase2.gui;

import org.GUIDisplay;
import org.phase2.mainloophelpers.controllerspresenters.GUIMAccountLoginPresenter;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;

import javax.swing.*;
import java.util.Arrays;


public class LoginUI {
    private JPanel mainPanel;
    private JPanel loggedOutPanel;
    private JLabel welcomeMessage;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel error;


    private final MAccountLoginValidator mAccountLoginValidator;
    private final GUIMAccountLoginPresenter mAccountLoginPresenter;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public LoginUI(GUIDisplay guiDisplay) {

        mainPanel.setVisible(true);

        this.mAccountLoginValidator = new MAccountLoginValidator();
        this.mAccountLoginPresenter = new GUIMAccountLoginPresenter(error);


        loginButton.addActionListener(e -> {
            String username = loginCommand("login");
            if (username == null){
                mAccountLoginPresenter.loginState(false);
                return;
            }

            mAccountLoginValidator.updateSave();
            guiDisplay.login(username, mAccountLoginValidator);
            guiDisplay.switchLoginView("login");
            guiDisplay.switchViews("standard");

            mAccountLoginPresenter.loginState(true);

        });
        registerButton.addActionListener(e -> {
            String username = loginCommand("register");
            if (username == null){
                mAccountLoginPresenter.registerState(false);
                return;
            }
            mAccountLoginPresenter.registerState(true);

            mAccountLoginValidator.updateSave();
        });
    }

    public String loginCommand(String cmd){
        switch (cmd){
            case "login" -> {
                if(validateAccountSignIn(usernameTextField.getText(), Arrays.toString(passwordPasswordField.getPassword()))){
                    return usernameTextField.getText();
                }else{
                    return null;
                }
            }
            case "register" -> {
                if(mAccountLoginValidator.registerUser(usernameTextField.getText(), Arrays.toString(passwordPasswordField.getPassword()))){
                    return usernameTextField.getText();
                }else {
                    return null;
                }
            }
        }
        return null;
    }
    public boolean validateAccountSignIn(String username, String password) {
        return mAccountLoginValidator.validateLogin(username, password);
    }
}
