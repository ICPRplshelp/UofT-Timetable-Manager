package org.phase2.gui.frameworksanddrivers;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerAdmin;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class AdminUI {
    private JPanel mainPanel;
    private JTextPane banTextPane;
    private JButton deleteUserButton;
    private JTextField banField;
    private JButton banUserButton;
    private JTextField deleteField;
    private JTextPane deleteTextPane;
    private JTextField createAdminUsernameField;
    private JButton createAdminButton;
    private JTextPane createAdminTextPane;
    private JTextField promoteAdminField;
    private JButton promoteAdminButton;
    private JTextPane promoteUserTextPane1;
    private JPasswordField createAdminPasswordField;
    private JTextField banTimeField;
    private JLabel error;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AdminUI(ControllerAdmin controller) {

        mainPanel.setVisible(true);
        banUserButton.addActionListener(e -> {

            String userToBan = banField.getText();
            String dateToParse = banTimeField.getText();
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                java.util.Date date = dateFormatter.parse(dateToParse);
                if (controller.banUser(userToBan, date)){
                    error.setText("User banned");
                } else {
                    error.setText("User not found, or you do not have permission for this action.");
                }
            } catch (ParseException ignored) {
                error.setText("Invalid date format");
            }
        });
        deleteUserButton.addActionListener(e -> {
            if (controller.deleteUser(deleteField.getText())){
                error.setText("User deleted");
            } else {
                error.setText("User not found, or you do not have permission for this action.");
            }
        });
        createAdminButton.addActionListener(e -> {
            if (controller.createNewAdminUser(new String[] {
                    createAdminUsernameField.getText(),
                    Arrays.toString(createAdminPasswordField.getPassword())})){
                error.setText("User created");
            }else{
                error.setText("User already exists, or you do not have permission for this action.");
            }
        });
        promoteAdminButton.addActionListener(e -> {
            if (controller.promoteUserToAdmin(promoteAdminField.getText())){
                error.setText("User promoted");
            } else {
                error.setText("User not found, or you do not have permission for this action.");
            }
        });
    }
}
