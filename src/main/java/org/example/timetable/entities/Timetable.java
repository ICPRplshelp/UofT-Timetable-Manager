package org.example.timetable.entities;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.util.*;

public class Timetable {
    private final List<CourseChoice> plannedFCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedSCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedYCourses  = new ArrayList<>();

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();



}
