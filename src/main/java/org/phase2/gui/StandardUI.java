package org.phase2.gui;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;

import javax.swing.*;

public class StandardUI {
    private JPanel mainPanel;
    private JButton getLoginHistoryButton;
    private JScrollPane courseHistoryPane;
    private JTextArea courseHistoryText;
    private final ControllerStandard cs;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StandardUI(ControllerStandard cs) {
        this.cs = cs;
        mainPanel.setVisible(true);
        getLoginHistoryButton.addActionListener(e -> courseHistoryText.setText(cs.getUserHistoryAsString()));
    }
}
