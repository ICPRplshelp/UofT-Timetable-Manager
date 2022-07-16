package org.example.timetable.entities;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warnings.TimetableWarning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timetable {
    private final List<CourseChoice> plannedFCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedSCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedYCourses  = new ArrayList<>();

    private final Map<String, TimetableWarning> warnings = new HashMap<>();

}
