package org.phase2.gui.frameworksanddrivers;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;

import javax.swing.*;

public class StandardUI {
    private JPanel mainPanel;
    private JButton getCourseHistoryButton;
    private JTextArea courseHistory;
    private final ControllerStandard cs;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StandardUI(ControllerStandard cs) {
        this.cs = cs;
        mainPanel.setVisible(true);
    }
}
