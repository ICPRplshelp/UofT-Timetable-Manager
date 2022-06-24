package org.example.coursegetter.entities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseStorage {
    private final Map<String, Course> courses;
    public final String session;
    // only UTSG courses are supported



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


}
