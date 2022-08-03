package org.phase2.gui.frameworksanddrivers;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JButton courseButton;

    private JButton meetingButton;

    private final ControllerCourseSearcher2 ccs2;
    public CourseSearchUI(ControllerCourseSearcher2 ccs2) {
        this.ccs2 = ccs2;

        mainPanel.setVisible(true);
        searchCoursesButton.addActionListener(e -> {
            Set<String> cc = ccs2.searchCurrentCourses(courseSearchField.getText());
            outputTextArea.setText(String.join("\n", cc));
        });
        searchMeetingsButton.addActionListener(e -> {
            Set<String> cc = ccs2.searchMeetings(meetingSearchField.getText());
            outputTextArea.setText(String.join("\n", cc));
        });
    }


}
