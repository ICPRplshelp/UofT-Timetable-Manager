package org.phase2.studentrelated.controllers;

import org.phase2.studentrelated.usecases.StudentManager;

public class StudentController {

    private final StudentManager sm;

    public StudentController(StudentManager sm) {
        this.sm = sm;
    }

    public boolean addCourse(String crsCode) {
        return sm.addToPlannedCourses(crsCode);
    }

    public boolean addHistoricalCourse(String crsCode) {
        return sm.addToPassedCourses(crsCode);
    }

    public boolean addMeetingToPlannedCourse(String crsCode, String meetingCode) {
        return sm.addMeetingToPlannedCourse(crsCode, meetingCode);
    }

    public boolean removePlannedCourse(String crsCode) {
        return sm.removeFromPlannedCourses(crsCode);
    }

    public boolean removeHistoricalCourse(String crsCode) {
        return sm.removeFromPassedCourses(crsCode);
    }


}
