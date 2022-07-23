package org.phase2.studentrelated.controllers;

import org.example.timetable.entities.warningtypes.WarningType;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is actually a presenter - I just have to rename this.
 */
public class ImplicitStudentController {

    private StudentManager sm;
    private WarningChecker2 warningChecker;

    public ImplicitStudentController(StudentManager sm) {
        this.sm = sm;
    }


}
