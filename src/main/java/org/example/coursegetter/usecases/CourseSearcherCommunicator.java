package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.baseclasses.Course;

public class CourseSearcherCommunicator {

    private final CourseSearcherIndividual csi;

    public CourseSearcherCommunicator(CourseSearcherIndividual csi) {
        this.csi = csi;
    }


    /**
     * Searches a course in a session by its course code.
     *
     * @param session the session in which to search for a course.
     * @param courseCode the code of the course.
     * @return null if there is no such course. Otherwise, return the course through CourseCommunicator.
     */
    public CourseCommunicator searchCourse(String session, String courseCode) {
        Course tempCourse = csi.getCourseOfferingByCode(session, courseCode);
        if (tempCourse != null) {
            return new CourseCommunicator(tempCourse);
        } else {
            return null;
        }
    }
}
