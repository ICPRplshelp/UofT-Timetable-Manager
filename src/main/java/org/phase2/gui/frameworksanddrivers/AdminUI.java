package org.phase2.gui.frameworksanddrivers;

import javax.swing.*;

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

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AdminUI() {
        mainPanel.setVisible(true);
    }
}
