package org.example.studentdata.entities;

import org.example.coursegetter.entities.Course;
import org.example.timetable.entities.Timetable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Student {
    private final Set<CourseChoice> plannedCourses = new TreeSet<>();
    private final Set<Course> previousCourses;

    private Timetable timetable;

    public Student() {
        previousCourses = new TreeSet<>();
    }

    /**
     * Returns a collection of courses the
     * student has already passed in all previous sections.
     *
     * @return a collection of courses the student has
     * already taken in previous sessions.
     */
    public Collection<Course> getAllPreviousCourses() {
        return previousCourses;
    }

    /**
     * Add all passed in courses to the collection of planned courses.
     * You may not add the same course twice, or it will be discarded.
     * @param plannedCourses a collection of planned courses.
     */
    public void addToPlannedCourses(Collection<CourseChoice> plannedCourses){
        this.plannedCourses.addAll(plannedCourses);
    }

    /**
     * Add all passed in courses to the collection of previous courses.
     * You may not add the same course twice, or it will be discarded.
     * @param previousCourses a collection of planned courses.
     */
    public void addToPreviousCourses(Collection<Course> previousCourses){
        this.previousCourses.addAll(previousCourses);
    }

    /**
     * Remove all passed in courses to the collection of previous courses.
     * @param plannedCourses a collection of planned courses.
     */
    public void removeFromPlannedCourses(Collection<CourseChoice> plannedCourses){
        this.plannedCourses.removeAll(plannedCourses);
    }

    /**
     * Remove all passed in courses to the collection of previous courses.
     * @param previousCourses a collection of planned courses.
     */
    public void removeFromPreviousCourses(Collection<Course> previousCourses){
        this.previousCourses.removeAll(previousCourses);
    }

    public Collection<CourseChoice> getPlannedFCourses() {
        return getPlannedCourses("F");
    }

    public Collection<CourseChoice> getPlannedSCourses() {
        return getPlannedCourses("S");
    }

    public Collection<CourseChoice> getPlannedYCourses() {
        return getPlannedCourses("Y");
    }

    private Collection<CourseChoice> getPlannedCourses(String section){
        Stream<CourseChoice> temp = plannedCourses.stream().filter(crs -> crs.getCourse().getSession().equals(section));
        return temp.collect(Collectors.toList());
    }


}
