package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CourseSearcherByKeyword {

    private final CourseSearcherIndividual csi;

    public CourseSearcherByKeyword(CourseSearcherIndividual csi) {
        this.csi = csi;
    }

    public List<String> getCoursesByKeyword(String keyword, String session) {
        List<Course> courseList = filterCourseNames(keyword, session);
        Collections.sort(courseList);

        List<String> courseCodes = new ArrayList<String>();

        for (Course course : courseList) {
            courseCodes.add(course.getCode());
        }

        return courseCodes;
    }

    private List<Course> filterCourseNames(String keyword, String session) {
        List<Course> coursesSoFar = new ArrayList<Course>();

        Set<String> allCourseOfferings = csi.getAllCoursesOfferingList(session);

        for (String code : allCourseOfferings) {
            if (code.startsWith(keyword)) {
                coursesSoFar.add(csi.getCourseOfferingByCode(session, code));
            }
        }
        return coursesSoFar;
    }
}