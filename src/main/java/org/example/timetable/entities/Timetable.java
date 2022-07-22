package org.example.timetable.entities;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Timetable  implements Serializable {


    private final Collection<CourseChoice> plannedCourses;
    private double previousCredits;

    private Collection<Course> previousCourses;

    public Timetable (Collection<CourseChoice> plannedCourses){
        this.plannedCourses = plannedCourses;
    }

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();

    /**
     * Adds a course to the timetable.
     *
     * @param courseChoice the course to add.
     */
    public void addToTimetable(CourseChoice courseChoice) {
        plannedCourses.add(courseChoice);
    }



    /**
     * @return all planned course choices so far.
     */
    public Collection<CourseChoice> getPlannedCourses(){
        return plannedCourses;
    }

    public Collection<CourseChoice> getPlannedCourses(String section){
        Stream<CourseChoice> temp = plannedCourses.stream().filter(crs -> crs.getCourse().getSession().equals(section));
        return temp.collect(Collectors.toList());
    }

    /**
     * Adds a warning to associate with a course.
     *
     * @param courseChoice the course to add a warning for.
     * @param timetableWarning the timetableWarning to add to a course.
     */
    public void addWarning(CourseChoice courseChoice, TimetableWarning timetableWarning){
        if (!warnings.containsKey(courseChoice)){
            CourseWarning warning = new CourseWarning();
            warning.addWarning(timetableWarning);
            warnings.put(courseChoice, warning);
        } else {
            CourseWarning warning = warnings.get(courseChoice);
            warning.addWarning(timetableWarning);
        }

    }

    public CourseWarning getWarning(CourseChoice courseChoice) {
        return warnings.get(courseChoice);
    }


    public void clearWarnings() {warnings.clear();}
    public double getPreviousCredits(){return previousCredits;}
    public void setPreviousCredits(double Credits) {previousCredits = Credits;}

    public Collection<Course> getPreviousCourses() {
        return previousCourses;
    }

    public void setPreviousCourses(Collection<Course> previousCourses) {
        this.previousCourses = previousCourses;
    }
}

