package org.example.studentdata.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Student {

    // all Strings MUST match the regex of a course offering.
    // e.g. CSC110Y1-F
    // the courses a student plans to take
    // based on the order it was added by the student
    // on the front-end side of this app

    // Exactly the same algorithm.
    private final List<CourseChoice> plannedFCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedSCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedYCourses  = new ArrayList<>();
    // Use cases may only touch these lists, so let the
    // use cases do whatever we want to them


    public void addToPlannedFCourses(List<CourseChoice> plannedCourses){
        plannedFCourses.addAll(plannedCourses);
    }

    public void addToPlannedSCourses(List<CourseChoice> plannedCourses){
        plannedSCourses.addAll(plannedCourses);
    }

    public void addToPlannedYCourses(List<CourseChoice> plannedCourses){
        plannedYCourses.addAll(plannedCourses);
    }

    public List<CourseChoice> getPlannedFCourses() {
        return plannedFCourses;
    }

    public List<CourseChoice> getPlannedSCourses() {
        return plannedSCourses;
    }

    public List<CourseChoice> getPlannedYCourses() {
        return plannedYCourses;
    }

    public List<CourseChoice> previousCourses = new ArrayList<>();

    public void flushFCourses(){
        flushCourses(plannedFCourses);
    }

    public void flushSCourses(){
        flushCourses(plannedSCourses);
    }

    public void flushYCourses(){
        flushCourses(plannedYCourses);
    }

    private void flushCourses(List<CourseChoice> plannedCourses){
        previousCourses.addAll(plannedCourses);
        plannedCourses.clear();


    }

    /**
     * Sort all the course lists.
     */
    public void sortAllCourseLists(){
        Collections.sort(plannedFCourses);
        Collections.sort(plannedSCourses);
        Collections.sort(plannedYCourses);
    }
}
