package org.example.studentrelated.controllers;

import org.example.htmltables.ConflictException;
import org.example.htmltables.NotOnHourException;
import org.example.htmltables.TableOrganizer;
import org.example.studentrelated.presenters.IScheduleEntry;
import org.example.studentrelated.presenters.StudentPresenter;
import org.example.studentrelated.usecases.StudentManager;
import org.example.studentrelated.usecases.WarningChecker;

import java.util.Map;
import java.util.Set;

public class StudentController {

    private final StudentManager sm;
    private final TableOrganizer fTable;
    private final TableOrganizer sTable;
    private final WarningChecker wc;


    public StudentController(StudentManager sm,
                             TableOrganizer fTable,
                             TableOrganizer sTable,
                             WarningChecker wc) {
        this.sm = sm;
        this.fTable = fTable;
        this.sTable = sTable;
        this.wc = wc;
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
    public StudentPresenter getPresenter() {
        return new StudentPresenter(wc, sm);
    }

    public Map<Character, Set<IScheduleEntry>> getPlannedCoursesSE() {
        return sm.getPlannedCourseSE();
    }

    /**
     * Gets the HTML table.
     *
     * @param term f or s not case-sensitive
     * @return the table as an HTML string
     */
    public String getTable(String term) {

        term = term.toUpperCase();
        if (fTable == null || sTable == null) return "";
        TableOrganizer to = term.toUpperCase().startsWith("F") ? fTable : sTable;
        char fs = term.startsWith("F") ? 'F' : 'S';
        // recheck timetable warnings - this runs only when needed
        wc.checkTimetableWarnings();
        String temp;
        try {
            temp = to.generateHTMLTable(getPlannedCoursesSE().get(fs));
        } catch (NotOnHourException | ConflictException e) {
            return to.getHTMLFailure();
        }
        return temp;
    }
}
