package org.phase2.gui.frameworksanddrivers;

import org.phase2.studentrelated.controllers.StudentController;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

public class StudentUI {
    private JPanel mainPanel;
    private JButton addCourseButton;
    private JButton removePastCourseButton;
    private JButton addPastCourseButton;
    private JButton addMeetingButton;
    private JButton removeCourseButton;
    private JTextField addCourseField;
    private JButton viewTimetableButton;
    private JTextField addMeetingField;
    private JTextArea timetableTextArea;
    private JTextField sessionTextField;
    private JEditorPane htmlEditorPane;
    private JLabel error;
    private JTextField addPastCourseField;

    private final StudentController stc;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StudentUI(StudentController stc) {
        this.stc = stc;
        mainPanel.setVisible(true);
        addCourseButton.addActionListener(e -> {
            if (stc.addCourse(addCourseField.getText())) {
                error.setText("Course added successfully");
            } else {
                error.setText("Course doesn't exist, or is already added");
            }
        });
        removeCourseButton.addActionListener(e -> {
            if (stc.removePlannedCourse(addCourseField.getText())){
                error.setText("Course removed successfully");
            } else {
                error.setText("Course wasn't in your planned courses");
            }
        });
        addPastCourseButton.addActionListener(e -> {
            if (stc.addHistoricalCourse(addPastCourseField.getText())){
                error.setText("Past course added successfully");
            } else {
                error.setText("Past course doesn't exist, you are taking that course currently, or is already added");
            }
        });
        removePastCourseButton.addActionListener(e -> {
            if (stc.removeHistoricalCourse(addPastCourseField.getText())){
                error.setText("Past course removed successfully");
            } else {
                error.setText("Past course wasn't in your past courses");
            }
        });
        addMeetingButton.addActionListener(e -> {
            if (stc.addMeetingToPlannedCourse(addCourseField.getText(), addMeetingField.getText())){
                error.setText("Meeting added successfully");
            } else {
                error.setText("Meeting doesn't exist, it's already added, or the course you are trying to add the meeting to doesn't exist");
            }
        });
        viewTimetableButton.addActionListener(e -> {
            // https://alvinalexander.com/blog/post/jfc-swing/how-create-simple-swing-html-viewer-browser-java/
            HTMLEditorKit kit = new HTMLEditorKit();
            htmlEditorPane.setEditorKit(kit);

            Document doc = kit.createDefaultDocument();
            htmlEditorPane.setDocument(doc);
            htmlEditorPane.setText(stc.getTable(sessionTextField.getText()));
        });
    }
}
