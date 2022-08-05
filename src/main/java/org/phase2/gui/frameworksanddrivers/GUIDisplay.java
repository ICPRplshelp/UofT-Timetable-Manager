package org.phase2.gui.frameworksanddrivers;

import org.example.PresenterPrinter;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.phase2.mainloophelpers.ui.GUIObjectPool;
import org.phase2.objectcreators.uifactories.GUIFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIDisplay {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel bodyPanel;
    private JPanel footerPanel;
    private JButton adminButton;
    private JButton searchButton;
    private JButton standardButton;
    private JButton studentButton;
    private JPanel loggedInPanel;
    private JButton logoutButton;
    private JPanel switchPanel;
    private JButton exitButton;

    private GUIObjectPool guiObjectPool;

    private final LoginUI loginUI;

    public GUIDisplay() {
        loginUI = new LoginUI(this);
        switchLoginView("logout");

        studentButton.addActionListener(e -> switchViews("student"));
        standardButton.addActionListener(e -> switchViews("standard"));
        searchButton.addActionListener(e -> switchViews("search"));
        adminButton.addActionListener(e -> switchViews("admin"));
        logoutButton.addActionListener(e -> switchLoginView("logout"));
        exitButton.addActionListener(e -> System.exit(0));
    }

    public void switchViews(String view){
        bodyPanel.removeAll();
        switch (view) {
            case "admin" -> bodyPanel.add(guiObjectPool.getAdmin());
            case "search" -> bodyPanel.add(guiObjectPool.getSearch());
            case "standard" -> bodyPanel.add(guiObjectPool.getStandard());
            case "student" -> bodyPanel.add(guiObjectPool.getStudent());
        }
        bodyPanel.repaint();
        bodyPanel.revalidate();
    }

    public void switchLoginView(String view){
        switchPanel.removeAll();
        switch (view) {
            case "login" -> switchPanel.add(loggedInPanel);
            case "logout" -> switchPanel.add(loginUI.getMainPanel());
        }
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI Display");
        GUIDisplay guiDisplay = new GUIDisplay();
        frame.setContentPane(guiDisplay.mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setSize(800,600);
        frame.setVisible(true);
    }


    public void login(String username, MAccountLoginValidator mAccountLoginValidator){
        GUIFactory guiFactory = new GUIFactory(username, mAccountLoginValidator);
        guiObjectPool = new GUIObjectPool(guiFactory);
    }
}
