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


    private final PresenterPrinter prt = new PresenterPrinter();

    public GUIDisplay() {
        loginUI = new LoginUI(this);
        switchLoginView("logout");


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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchLoginView("logout");
            }
        });
    }

    // there are the methods that will be kept

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
        frame.setSize(800,400);
        frame.setVisible(true);
    }


    public void login(String username, MAccountLoginValidator mAccountLoginValidator){
        GUIFactory guiFactory = new GUIFactory(username, mAccountLoginValidator);
        guiObjectPool = new GUIObjectPool(guiFactory);
    }
}
