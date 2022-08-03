package org.phase2.gui.frameworksanddrivers;

import javax.swing.*;

public class StudentUI {
    private JPanel mainPanel;
    private JButton addCourseButton;
    private JButton removePastCourseButton;
    private JButton addPastCourseButton;
    private JButton addMeetingButton;
    private JButton removeCourseButton;
    private JTextField addCourseField;
    private JTextField addMeetingField;
    private JTextField addPastField;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StudentUI() {
        mainPanel.setVisible(true);
    }
}
