package org.phase2.gui.frameworksanddrivers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton button1;
    private JButton button2;

    public GUIDisplay() {

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
        search = new CourseSearchUI().getMainPanel();
        student = new StudentUI().getMainPanel();
        admin = new AdminUI().getMainPanel();
    }

    // there are the methods that will be kept

    public void switchViews(String view){
        bodyPanel.removeAll();
        switch (view) {
            case "admin" -> bodyPanel.add(admin);
            case "search" -> {
                bodyPanel.add(search);
                search.validate();
                search.setVisible(true);
            }
            case "standard" -> bodyPanel.add(standard);
            case "student" -> bodyPanel.add(student);
        }
        bodyPanel.repaint();
        bodyPanel.revalidate();
    }

    public void logout(){
        switchPanel.removeAll();
        switchPanel.add(loggedOutPanel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public void login(){
        switchPanel.removeAll();
        switchPanel.add(loggedInPanel);
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
}
