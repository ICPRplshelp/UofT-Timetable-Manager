package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.baseclasses.Course;
import org.phase2.studentrelated.usecases.CourseSearcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CourseSearcherByKeyword {

    private final CourseSearcher csa;

    public CourseSearcherByKeyword(CourseSearcher csa) {
        this.csa = csa;
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

        List<String> courseCodes = new ArrayList<>();

        for (Course course : courseList) {
            courseCodes.add(course.getOfferingCode());
        }

        return courseCodes;
    }

    private List<Course> filterCourseNames(String keyword, String session) {
        List<Course> coursesSoFar = new ArrayList<>();

        Set<String> allCourseOfferings = csa.getAllCoursesOfferingList(session);

        for (String code : allCourseOfferings) {
            if (code.startsWith(keyword)) {
                coursesSoFar.add(csa.getCourseOfferingByCode(session, code));
            }
        }
        return coursesSoFar;
    }
}
