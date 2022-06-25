package org.example.coursegetter.entities;

import java.util.Map;
import java.util.Set;

public class CourseStorage {
    private final Map<String, Course> courses;
    public final String session;
    // only UTSG courses are supported


    /**
     * Create a course storage.
     * @param courseMap a map of courses.
     * @param session the session of the courses.
     */
    public CourseStorage(Map<String, Course> courseMap, String session) {
        this.courses = courseMap;
        this.session = session;
    }

    /**
     * Gets a course.
     *
     * @param fullCourseCode the course code, in a format similar to CSC110Y1-F-20229
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourse(String fullCourseCode){
        return courses.get(fullCourseCode);
    }

    /**
     * Gets a set of all the course offering codes.
     * The set is a copy, so mutating will not
     * affect the original course storage.
     *
     * @return a set of all the course codes.
     */
    public Set<String> getCourseOfferingListAsString(){
        Set<String> tempKeySet = courses.keySet();
        Set<String> newSet = new java.util.HashSet<>();
        tempKeySet.forEach(key -> newSet.add(key.substring(0, key.length() - 6)));
        return newSet;
    }

    /**
     * Gets a set of all the course codes.
     * The set is a copy, so mutating will not
     * affect the original course storage.
     *
     * @return a set of all the course codes.
     */
    public Set<String> getCourseListAsString(){
        Set<String> tempKeySet = courses.keySet();
        Set<String> newSet = new java.util.HashSet<>();
        tempKeySet.forEach(key -> newSet.add(key.substring(0, key.length() - 8)));
        return newSet;
    }
}
