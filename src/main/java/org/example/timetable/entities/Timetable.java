package org.example.timetable.entities;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.util.*;

public class Timetable {
    private final Set<CourseChoice> plannedFCourses  = new HashSet<>();
    private final Set<CourseChoice> plannedSCourses  = new HashSet<>();
    private final Set<CourseChoice> plannedYCourses  = new HashSet<>();

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();



}
