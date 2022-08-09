package org.phase2.gui;

import org.example.logincode.controllerspresentersgateways.gateways.StorageLoader;
import org.example.logincode.usecases.StorageManager;
import org.phase2.studentrelated.controllers.StudentController;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import java.util.Collection;

public class StudentUI {
    private JPanel mainPanel;
    private JButton addCourseButton;
    private JButton removePastCourseButton;
    private JButton addPastCourseButton;
    private JButton addMeetingButton;
    private JButton removeCourseButton;
    private JTextField addCourseField;
    private JTextField addMeetingField;
    private JButton fallSessionButton;
    private JButton winterSessionButton;

    private JEditorPane htmlEditorPane;
    private JLabel error;
    private JTextField addPastCourseField;
    private JTextArea coursesTextArea;
    private JLabel plannedCoursesTitle;
    private JTextArea pCoursesTextArea;

    private final StudentController stc;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void saveState() {
        SaveStateController.getInstance().updateSave();
    }

    public StudentUI(StudentController stc) {
        this.stc = stc;
        mainPanel.setVisible(true);
        displayCourseList();
        addCourseButton.addActionListener(e -> {
            if (stc.addCourse(addCourseField.getText())) {
                displayCourseList();
                error.setText("Course added successfully");
            } else {
                error.setText("Course doesn't exist, or is already added");
            }
            displayCourseList();
        });
        removeCourseButton.addActionListener(e -> {
            if (stc.removePlannedCourse(addCourseField.getText())){
                displayCourseList();
                error.setText("Course removed successfully");
            } else {
                error.setText("Course wasn't in your planned courses");
            }
            displayCourseList();
        });
        addPastCourseButton.addActionListener(e -> {
            if (stc.addHistoricalCourse(addPastCourseField.getText())){
                error.setText("Past course added successfully");
            } else {
                error.setText("Past course doesn't exist, you are taking that course currently, or is already added");
            }
            displayCourseList();
        });
        removePastCourseButton.addActionListener(e -> {
            if (stc.removeHistoricalCourse(addPastCourseField.getText())){
                error.setText("Past course removed successfully");
            } else {
                error.setText("Past course wasn't in your past courses");
            }
            displayCourseList();
        });
        addMeetingButton.addActionListener(e -> {
            if (stc.addMeetingToPlannedCourse(addCourseField.getText(), addMeetingField.getText())){
                error.setText("Meeting added successfully");
            } else {
                error.setText("Meeting doesn't exist, it's already added, or the course you are trying to add the meeting to wasn't planned");
            }
            displayCourseList();
        });

        fallSessionButton.addActionListener(e -> {
            HTMLEditorKit kit = new HTMLEditorKit();
            htmlEditorPane.setEditorKit(kit);

            Document doc = kit.createDefaultDocument();
            htmlEditorPane.setDocument(doc);
            htmlEditorPane.setText(stc.getTable("F"));
        });
        winterSessionButton.addActionListener(e -> {
            HTMLEditorKit kit = new HTMLEditorKit();
            htmlEditorPane.setEditorKit(kit);

            Document doc = kit.createDefaultDocument();
            htmlEditorPane.setDocument(doc);
            htmlEditorPane.setText(stc.getTable("S"));
        });
    }

    /**
     * Displays the course list and also saves the state of the program.
     */
    private void displayCourseList() {
        saveState();
        coursesTextArea.setText(
                joinCollection(stc.getPresenter().getPlannedCourseInfo()));
        pCoursesTextArea.setText(joinCollection(stc.getPresenter().getPassedCourseInfo()));
    }

    private String joinCollection(Collection<String> col){
        return String.join("\n", col);
    }
}
