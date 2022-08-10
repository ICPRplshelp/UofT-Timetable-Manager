package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.baseclasses.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This class may be used to:
 * - Return a list of all courses whose course offering code contains a given keyword
 * <p>
 * This class may not be used to look up information about any individual course
 */
public class CourseSearcherByKeyword {

    private final CourseSearcherIndividual csi;

    public CourseSearcherByKeyword(CourseSearcherIndividual csi) {
        this.csi = csi;
    }

    /**
     * Finds courses by a keyword (i.e. the course title or part of the course title.
     *
     * @param keyword words used in the search.
     * @param session the session in which to search for courses
     * @return a list of course codes.
     */
    public List<String> getCoursesByKeyword(String keyword, String session) {
        List<Course> courseList = filterCourseNames(keyword, session);
        Collections.sort(courseList);

        List<String> courseOfferings = new ArrayList<>();

        for (Course course : courseList) {
            courseOfferings.add(course.getOfferingCode());
        }

        return courseOfferings;
    }

    private List<Course> filterCourseNames(String keyword, String session) {
        List<Course> coursesSoFar = new ArrayList<>();

        Set<String> allCourseOfferings = csi.getAllCoursesOfferingList(session);

        for (String offering : allCourseOfferings) {
            if (offering.startsWith(keyword)) {
                coursesSoFar.add(csi.getCourseOfferingByCode(session, offering));
            }
        }
        return coursesSoFar;
    }
}
