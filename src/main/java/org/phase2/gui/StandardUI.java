package org.phase2.gui;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;

import javax.swing.*;

public class StandardUI {
    private JPanel mainPanel;
    private JButton getLoginHistoryButton;
    private JScrollPane courseHistoryPane;
    private JTextArea courseHistoryText;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StandardUI(ControllerStandard cs) {
        mainPanel.setVisible(true);
        getLoginHistoryButton.addActionListener(e -> courseHistoryText.setText(cs.getUserHistoryAsString()));
    }
}
