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

    /**
     *
     * @return all planned course choices so far.
     */
    public Collection<CourseChoice> getPlannedCourses(){
        Collection<CourseChoice> f = getPlannedCourses("F");
        Collection<CourseChoice> s = getPlannedCourses("S");
        Collection<CourseChoice> y = getPlannedCourses("Y");
        return Stream.concat(f.stream(), Stream.concat(s.stream(), y.stream())).collect(Collectors.toList());
    }

    /**
     *
     * @param section target section.
     * @return all planned course choices for the target section.
     */
    public Collection<CourseChoice> getPlannedCourses(String section){
        switch (section) {
            case "F" -> { return plannedFCourses;}
            case "S" -> { return plannedSCourses;}
            default -> { return plannedYCourses;} // Y
        }
    }

//    public Set<CourseChoice> getPlannedCourses(Section section) {
//        return switch (section){
//            case F -> plannedFCourses;
//            case S -> plannedSCourses;
//            case Y -> plannedYCourses;
//        };
//    }

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

