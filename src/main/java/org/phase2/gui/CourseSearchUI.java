package org.phase2.gui;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;

import javax.swing.*;
import java.util.Set;

public class CourseSearchUI extends JPanel {
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JButton searchCoursesButton;
    private JButton searchMeetingsButton;
    private JTextField meetingSearchField;
    private JTextField courseSearchField;
    private JTextArea outputTextArea;
    private JButton smartSearchCoursesButton;

    private JButton courseButton;

    private JButton meetingButton;

    public CourseSearchUI(ControllerCourseSearcher2 ccs2) {

        mainPanel.setVisible(true);
        searchCoursesButton.addActionListener(e -> {
            Set<String> cc = ccs2.searchCurrentCourses(courseSearchField.getText());
            outputTextArea.setText(String.join("\n", cc));
        });
        searchMeetingsButton.addActionListener(e -> {
            Set<String> cc = ccs2.searchMeetings(meetingSearchField.getText());
            outputTextArea.setText(String.join("\n", cc));
        });
        smartSearchCoursesButton.addActionListener(e -> {
            Set<String> cc = ccs2.searchCoursesICanTake(courseSearchField.getText());
            outputTextArea.setText(String.join("\n", cc));
        });
    }


}
