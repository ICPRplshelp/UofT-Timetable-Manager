package org.example.timetable.entities;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Timetable {
    private final Set<CourseChoice> plannedFCourses  = new HashSet<>();
    private final Set<CourseChoice> plannedSCourses  = new HashSet<>();
    private final Set<CourseChoice> plannedYCourses  = new HashSet<>();

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();

    public void addToTimetable(CourseChoice courseChoice) {
        switch (courseChoice.getCourse().getSession()) {
            case "F" ->  plannedFCourses.add(courseChoice);
            case "S" -> plannedSCourses.add(courseChoice);
            default -> plannedYCourses.add(courseChoice); // Y
        }
    }

    public void removeFromTimetable(CourseChoice courseChoice) {
        switch (courseChoice.getCourse().getSession()) {
            case "F" ->  plannedFCourses.remove(courseChoice);
            case "S" -> plannedSCourses.remove(courseChoice);
            default -> plannedYCourses.remove(courseChoice); // Y
        }
    }

    public Collection<CourseChoice> getPlannedCourses(String section){
        switch (section) {
            case "F" -> { return plannedFCourses;}
            case "S" -> { return plannedSCourses;}
            default -> { return plannedYCourses;} // Y
        }
    }



}
