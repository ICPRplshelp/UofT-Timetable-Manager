package org.phase2.gui.frameworksanddrivers;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerAdmin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {
    private JPanel mainPanel;
    private JTextPane banTextPane;
    private JButton deleteUserButton;
    private JTextField textField1;
    private JButton banUserButton;
    private JTextField textField2;
    private JTextPane deleteTextPane;
    private JTextField textField3;
    private JButton createAdminButton;
    private JTextPane createAdminTextPane;
    private JTextField textField4;
    private JButton promoteAdminButton;
    private JTextPane promoteUserTextPane1;

    private final ControllerAdmin controller;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AdminUI(ControllerAdmin controller) {
        this.controller = controller;

        mainPanel.setVisible(true);
        banUserButton.addActionListener(e -> {

        });
        deleteUserButton.addActionListener(e -> {

        });
        createAdminButton.addActionListener(e -> {

        });
        promoteAdminButton.addActionListener(e -> {

        });
    }
}
