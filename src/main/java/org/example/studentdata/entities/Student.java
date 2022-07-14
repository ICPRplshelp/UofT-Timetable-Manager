package org.example.studentdata.entities;

import java.util.Collections;
import java.util.List;


// TODO: This is an entity. Place it in an entity package.
public class Student {

    // TODO: Delete all TODOs after completely finishing them

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

    // TODO: Implement a way to move all courses in plannedFCourses to the one below
    public List<String> previousCourses;

    /**
     * Sort all the course lists.
     */
    public void sortAllCourseLists(){
        Collections.sort(plannedFCourses);
        Collections.sort(plannedSCourses);
        Collections.sort(plannedYCourses);
    }
}
