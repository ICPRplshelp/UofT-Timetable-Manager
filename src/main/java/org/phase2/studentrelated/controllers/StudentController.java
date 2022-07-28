package org.phase2.studentrelated.controllers;

import org.phase2.studentrelated.presenters.IScheduleEntry;
import org.phase2.studentrelated.presenters.StudentPresenter;
import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class StudentController {

    private final StudentManager sm;

    public StudentController(StudentManager sm) {
        this.sm = sm;
    }

    /**
     * Adds crsCode to a student's planned courses
     *
     * @param crsCode the course code, including the suffix (-F/-S/-Y)
     * @return whether addition was successful or not
     */
    public boolean addCourse(String crsCode) {
        return sm.addToPlannedCourses(crsCode);
    }

    /**
     * Adds crsCode to a student's historical courses
     *
     * @param crsCode the course code, without the suffix (-F/-S/-Y)
     * @return whether addition was successful or not
     */
    public boolean addHistoricalCourse(String crsCode) {

        return sm.addToPassedCourses(crsCode);
    }

    /**
     * Adds or replaces a lecture/tutorial/practical section to a planned course.
     *
     * @param crsCode     the planned course code, including the suffix (-F/-S/-Y)
     * @param meetingCode the meeting code, e.g. LEC0101 or TUT0101 or PRA0101
     * @return whether addition was successful.
     * There could be more than one
     * reason for failure.
     * The reasons for failure include:
     * - Course was never planned
     * - Meeting code did not exist
     */
    public boolean addMeetingToPlannedCourse(String crsCode, String meetingCode) {
        return sm.addMeetingToPlannedCourse(crsCode, meetingCode);
    }

    /**
     * Removes a course from a student's planned courses.
     *
     * @param crsCode the course code, including the suffix (-F/-S/-Y)
     * @return whether something was removed.
     * The only reason for failure
     * is that the course was never planned.
     */
    public boolean removePlannedCourse(String crsCode) {
        return sm.removeFromPlannedCourses(crsCode);
    }

    /**
     * Removes a course from a student's historical courses.
     *
     * @param crsCode the course code, WITHOUT the suffix (-F/-S/-Y)
     * @return whether something was removed.
     * The only reason for failure
     * is that the course was never there in the first place.
     */
    public boolean removeHistoricalCourse(String crsCode) {
        return sm.removeFromPassedCourses(crsCode);
    }

    /**
     * Obtains the presenter.
     */
    public StudentPresenter getPresenter(){
        WarningChecker2 wc2 = new WarningChecker2(sm.getCSA(), sm.getCSAP());
        return new StudentPresenter(wc2, sm);
    }

    public Map<Character, Set<IScheduleEntry>> getPlannedCoursesSE() {
        return sm.getPlannedCourseSE();
    }
}
