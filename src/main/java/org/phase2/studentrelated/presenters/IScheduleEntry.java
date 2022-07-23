package org.phase2.studentrelated.presenters;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * This class contains enough data for a single timetable
 * cell to display.
 *
 */
public interface IScheduleEntry {

    String getCourseCode();
    String getMeetingCode();
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
