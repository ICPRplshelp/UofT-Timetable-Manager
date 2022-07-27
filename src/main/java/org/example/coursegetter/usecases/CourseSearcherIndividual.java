package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.SessionStorage;

import java.util.Collection;
import java.util.Set;

/**
 * This class may be used to:
 * - Return all searchable code
 * - Get information about one course
 * <p>
 * This class may not be used to query multiple courses.
 * That's too overwhelming to implement.
 */
public class CourseSearcherIndividual {

    private final SessionStorage sessionStorage;
    private final CourseInputValidator courseInputValidator = new CourseInputValidator();

    CourseSearcherIndividual(SessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    /**
     * Gets a course.
     *
     * @param crsCode the course code, in a format similar to CSC110Y1-F
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourseOfferingByCode(String session, String crsCode) {
        String searchableCourse = courseInputValidator.courseOfferingToSearchableCourse(crsCode);
        if (searchableCourse == null) return null;
        return sessionStorage.getSession(session).getCourse(searchableCourse);
    }

    /**
     * Returns a set of all courses that can be reached from the given course
     * storage.
     * The set may not be modified.
     *
     * @return a set of all courses that can be reached from the given course storage.
     */
    public Set<String> getAllCoursesOfferingList(String session) {
        return sessionStorage.getSession(session).getCourseOfferingListAsString();
    }

}
