package org.example.timetable.usecases;

import org.example.studentdata.entities.Student;

public class TimetableCallbackFNs {
    private final Student student;
    private final TimetableManager timetableManager;

    public TimetableCallbackFNs(Student student, TimetableManager timetableManager) {
        this.student = student;
        this.timetableManager = timetableManager;
    }

    /**
     * Update timetable with current student data.
     */
    public void updateTimetable(){
        timetableManager.clearTimetable();
        timetableManager.addToTimeTable(student.getPlannedCourses());
    }
}
