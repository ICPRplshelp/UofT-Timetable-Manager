package org.example.timetable.usecases;

import org.example.studentdata.entities.CourseChoice;

import java.util.Collection;

import org.example.timetable.entities.Timetable;

public class TimetableManager {

    private final Timetable timetable;
    public TimetableManager(Timetable timetable) {
        this.timetable = timetable;
    }

    /**
     * Adds a list of courses to the timetable.
     *
     * @param courseList the list of courses to add.
     * @return true;
     */
    public boolean addToTimeTable(Collection<CourseChoice> courseList) {
        for (CourseChoice courseChoice : courseList) {
            timetable.addToTimetable(courseChoice);
        }
        return true;
    }

    public Timetable getTimetable() {
        return timetable;
    }

}
