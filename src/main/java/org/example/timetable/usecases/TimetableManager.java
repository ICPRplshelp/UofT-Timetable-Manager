package org.example.timetable.usecases;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.Timetable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TimetableManager {

    private final Timetable timetable;
    public TimetableManager(Timetable timetable) {
        this.timetable = timetable;
    }

    public boolean addToTimeTable(Collection<CourseChoice> courseList) {
        for (CourseChoice courseChoice : courseList) {
            timetable.addToTimetable(courseChoice);
        }
        return true;
    }

    //public List<CourseChoice> createPermutation() {

    //}

    public HashMap<String, List<List<String>>> checkIfTimetableConflict() {
        List<CourseChoice> choiceList = timetable.getPlannedCourses().stream().toList();
        CourseConflictChecker conflictChecker = new CourseConflictChecker(timetable);
        for (int i = 0; i < choiceList.size(); i++) {
            for (CourseChoice courseChoice : choiceList) {
                conflictChecker.checkIfConflict(choiceList.get(i).getCourse().getCode(),
                        courseChoice.getCourse().getCode());
            }
        }
        return conflictChecker.getConflictMap();
    }

    public Timetable getTimetable() {
        return timetable;
    }
    public void clearTimetable() {}
}
