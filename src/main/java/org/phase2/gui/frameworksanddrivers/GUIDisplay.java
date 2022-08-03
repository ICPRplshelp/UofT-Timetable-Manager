package org.phase2.gui.frameworksanddrivers;

import org.example.PresenterPrinter;
import org.phase2.mainloophelpers.controllerspresenters.GUIMAccountLoginPresenter;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginPresenter;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.phase2.objectcreators.uifactories.UIFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.ConsoleHandler;

public class GUIDisplay {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JButton adminButton;
    private JButton searchButton;
    private JButton standardButton;
    private JButton studentButton;
    private JPanel admin;
    private JPanel search;
    private JPanel standard;
    private JPanel student;
    private JPanel loggedInPanel;
    private JPanel switchPanel;
    private JPanel loggedOutPanel;
    private JLabel welcomeMessage;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JPanel footerPanel;
    private JButton exitButton;
    private JButton logoutButton;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel error;

    private final MAccountLoginValidator mAccountLoginValidator;
    private final GUIMAccountLoginPresenter mAccountLoginPresenter;

    private final PresenterPrinter prt = new PresenterPrinter();

    public GUIDisplay() {

        this.mAccountLoginValidator = new MAccountLoginValidator();
        this.mAccountLoginPresenter = new GUIMAccountLoginPresenter(error);

        search = new CourseSearchUI().getMainPanel();
        student = new StudentUI().getMainPanel();
        admin = new AdminUI().getMainPanel();
        standard = new StandardUI().getMainPanel();
        // these are temporary buttons to test the GUI, they will be removed later
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchViews("student");
            }
        });
        standardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchViews("standard");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchViews("search");
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchViews("admin");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginCommand("login");
                if (username == null){
                    mAccountLoginPresenter.loginState(false);
                    return;
                }

                mAccountLoginValidator.updateSave();
                switchLoginView("login");
                switchViews("standard");

                mAccountLoginPresenter.loginState(true);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchLoginView("logout");
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginCommand("register");
                if (username == null){
                    mAccountLoginPresenter.registerState(false);
                    return;
                }
                mAccountLoginPresenter.registerState(true);

                mAccountLoginValidator.updateSave();
                switchLoginView("register");

            }
        });
    }

    // there are the methods that will be kept

    public void switchViews(String view){
        bodyPanel.removeAll();
        switch (view) {
            case "admin" -> bodyPanel.add(admin);
            case "search" -> bodyPanel.add(search);
            case "standard" -> bodyPanel.add(standard);
            case "student" -> bodyPanel.add(student);
        }
        bodyPanel.repaint();
        bodyPanel.revalidate();
    }

    public void switchLoginView(String view){
        switchPanel.removeAll();
        switch (view) {
            case "login" -> switchPanel.add(loggedInPanel);
            case "logout" -> switchPanel.add(loggedOutPanel);
        }
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI Display");
        frame.setContentPane(new GUIDisplay().mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



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
