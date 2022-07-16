package org.example.timetable.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;
import org.example.timetable.entities.Timetable;

import java.util.List;

public class TimetableManager {

    Timetable timetable;
    public TimetableManager(Timetable timetable) {
        this.timetable = timetable;
    }

    public boolean addToTimeTable(List<CourseChoice> courseList) {
        for (CourseChoice courseChoice : courseList) {
            timetable.addToTimetable(courseChoice);
        }
        return true;
    }

    //public List<CourseChoice> createPermutation() {

    //}
}
