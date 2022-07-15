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
    public List<CourseChoice> plannedFCourses;
    public List<CourseChoice> plannedSCourses;
    public List<CourseChoice> plannedYCourses;
    // Use cases may only touch these lists, so let the
    // use cases do whatever we want to them

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
