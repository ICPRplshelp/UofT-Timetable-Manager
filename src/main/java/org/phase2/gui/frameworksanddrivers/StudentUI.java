package org.phase2.gui.frameworksanddrivers;

import org.phase2.studentrelated.controllers.StudentController;

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
    private JButton getHTMLButton;
    private JButton viewTimetableButton;

    private final StudentController stc;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StudentUI(StudentController stc) {
        this.stc = stc;
        mainPanel.setVisible(true);
    }
}
