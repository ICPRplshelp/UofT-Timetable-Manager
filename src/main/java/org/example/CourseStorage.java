package org.example;

import org.example.entities.courserelated.Course;

import java.util.Map;

public class CourseStorage {
    private final Map<String, Course> courses;
    private final String session;

    public CourseStorage(Map<String, Course> courseMap, String session){
        this.courses = courseMap;
        this.session = session;
    }

    /**
     * Gets a course.
     * @param crsCode the course code, in a format similar to CSC110Y1-F
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourseByCode(String crsCode){
        // TODO: check if crsCode matches the regex [A-Z]{3}[0-4][0-9]{3}[H|Y][0159]
        return courses.get(crsCode + "-" + this.session);
    }

}
