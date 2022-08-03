package org.phase2.gui.frameworksanddrivers;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;

import javax.swing.*;

public class CourseSearchUI extends JPanel {
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JButton searchCoursesButton;
    private JButton searchMeetingsButton;
    private JTextField textField1;
    private JTextField textField2;

    private JButton courseButton;

    private JButton meetingButton;

    private final ControllerCourseSearcher2 ccs2;
    public CourseSearchUI(ControllerCourseSearcher2 ccs2) {
        this.ccs2 = ccs2;

        mainPanel.setVisible(true);
    }


}
