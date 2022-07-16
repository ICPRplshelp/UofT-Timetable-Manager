package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.Course;

public class CourseSearcherCommunicator {

    private final CourseSearcherIndividual csi;

    public CourseSearcherCommunicator(CourseSearcherIndividual csi) {
        this.csi = csi;
    }


    public CourseCommunicator searchCourse(String session, String courseCode) {
        Course tempCourse = csi.getCourseOfferingByCode(session, courseCode);
        if (tempCourse != null) {
            return new CourseCommunicator(tempCourse);
        } else {
            return null;
        }
    }
}
