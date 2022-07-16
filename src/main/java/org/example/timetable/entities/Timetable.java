package org.example.timetable.entities;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.util.*;

public class Timetable {
    private final Set<CourseChoice> plannedFCourses  = new HashSet<>();
    private final Set<CourseChoice> plannedSCourses  = new HashSet<>();
    private final Set<CourseChoice> plannedYCourses  = new HashSet<>();

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();

    public Set<CourseChoice> getPlannedCourses(Section section) {
        return switch (section){
            case F -> plannedFCourses;
            case S -> plannedSCourses;
            case Y -> plannedYCourses;
        };
    }

    public void addWarning(CourseChoice courseChoice, TimetableWarning timetableWarning){
        if (!warnings.containsKey(courseChoice)){
            warnings.put(courseChoice, new CourseWarning());
        }

        warnings.get(courseChoice).addWarning(timetableWarning);

    }

    public CourseWarning getWarning(CourseChoice courseChoice) {
        return warnings.get(courseChoice);
    }
}

