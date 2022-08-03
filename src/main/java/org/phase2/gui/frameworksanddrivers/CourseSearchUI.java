package org.phase2.gui.frameworksanddrivers;

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
    public CourseSearchUI() {

        mainPanel.setVisible(true);
    }


}
