package org.example.coursegetter.usecases.internal;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.CourseStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * This class may be used to:
 * - Return all searchable code
 * - Get information about one course
 *
 * This class may not be used to query multiple courses.
 * That's too overwhelming to implement.
 */
public class CourseSearcherIndividual {

    private final CourseStorage courseStorage;
    private final CourseInputValidator courseInputValidator = new CourseInputValidator();

    public CourseSearcherIndividual(CourseStorage courseStorage) {
        this.courseStorage = courseStorage;
    }

    /**
     * Given a course code, this method searches for all course
     * offerings by this course code.
     * @param crsCode the course code such as MAT135H1
     * @return a collection of courses such as MAT135H1-F, MAT135H1-S, MAT135H1-Y
     */
    public Collection<Course> getCourseByCourseCode(String crsCode){
        crsCode = courseInputValidator.courseToSearchableCourse(crsCode);
        ArrayList<Course> courseList = new ArrayList<>();
        String[] suffixes = {"-F", "-S", "-Y"};
        for(String suffix : suffixes){
            String courseToSearch = crsCode + suffix;
            Course tempCourse = getCourseOfferingByCode(courseToSearch);
            if(tempCourse != null)
                courseList.add(tempCourse);
        }
        return courseList;
    }

    /**
     * Gets a course.
     *
     * @param crsCode the course code, in a format similar to CSC110Y1-F
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourseOfferingByCode(String crsCode) {
        String searchableCourse = courseInputValidator.courseOfferingToSearchableCourse(crsCode);
        if(searchableCourse == null) return null;
        searchableCourse += "-" + courseStorage.session;
        return courseStorage.getCourse(searchableCourse);
    }

    /**
     * Returns a set of all courses that can be reached from the given course
     * storage.
     *
     * @return a set of all courses that can be reached from the given course storage.
     */
    public Set<String> getAllCoursesOfferingList(){
        return courseStorage.getCourseOfferingListAsString();
    }

    /**
     * Returns a set of all courses that can be reached from the given course
     * storage.
     *
     * @return a set of all courses that can be reached from the given course storage.
     */
    public Set<String> getAllCoursesList(){
        return courseStorage.getCourseListAsString();
    }

}
