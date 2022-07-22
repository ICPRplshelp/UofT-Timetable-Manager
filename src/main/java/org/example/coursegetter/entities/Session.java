package org.example.coursegetter.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Session {
    private final Map<String, Course> courses;
    private final Set<String> courseList;


    // only UTSG courses are supported


    /**
     * Create a course storage.
     *
     * @param courseMap a map of courses.
     * @param session   the session of the courses.
     */
    public Session(Map<String, Course> courseMap, String session) {
        this.courses = courseMap;
        Set<String> tempKeySet = courses.keySet();
        courseList = new HashSet<>();

        tempKeySet.forEach(key -> courseList.add(key.substring(0, 8)));
    }

    /**
     * Gets a course.
     *
     * @param fullCourseCode the course code, in a format similar to CSC110Y1-F
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourse(String fullCourseCode) {
        return courses.get(fullCourseCode);
    }

    /**
     * Gets a set of all the course offering codes.
     * The set may not be modified.
     *
     * @return a set of all the course codes.
     */
    public Set<String> getCourseOfferingListAsString() {
        return Collections.unmodifiableSet(courses.keySet());
    }

}
