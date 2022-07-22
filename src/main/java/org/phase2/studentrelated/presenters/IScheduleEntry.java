package org.phase2.studentrelated.presenters;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * ScheduleEntry implements this.
 *
 * Anything implementing this may be used by a presenter
 * given it is typed ICourse.
 * For example,
 * Set[IScheduleEntry] ise = getCourseInfo("CSC110Y1-F");
 * is permitted in a presenter.
 */
public interface IScheduleEntry {

    /**
     * Returns the course code.
     */
    String getCourseCode();

    /**
     * Returns the F term meeting room.
     */
    String getAssignedRoom1();

    /**
     * Returns the S term meeting room.
     */
    String getAssignedRoom2();

    /**
     * Returns the start time of the meeting, or null if the
     * meeting is async.
     */
    LocalTime getStartTime();

    /**
     * Returns the end time of the meeting, or null
     * if the meeting is async.
     */
    LocalTime getEndTime();

    /**
     * Returns the day of the meeting as
     * a DayOfWeek enum, or null if the
     * meeting is async.
     */
    DayOfWeek getDay();
}
