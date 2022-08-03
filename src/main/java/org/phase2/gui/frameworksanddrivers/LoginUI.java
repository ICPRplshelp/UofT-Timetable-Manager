package org.phase2.gui.frameworksanddrivers;

import org.phase2.mainloophelpers.controllerspresenters.GUIMAccountLoginPresenter;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            guiDisplay.switchLoginView("register");
        });
    }

    public String loginCommand(String cmd){
        switch (cmd){
            case "login" -> {
                if(validateAccountSignIn(usernameTextField.getText(), passwordPasswordField.getText())){
                    return usernameTextField.getText();
                }else{
                    return null;
                }
            }
            case "register" -> mAccountLoginValidator.registerUser(usernameTextField.getText(), passwordPasswordField.getText());
        }
        return null;
    }
    public boolean validateAccountSignIn(String username, String password) {
        return mAccountLoginValidator.validateLogin(username, password);
    }
}
