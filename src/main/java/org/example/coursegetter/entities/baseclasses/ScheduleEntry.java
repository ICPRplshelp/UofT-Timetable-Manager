package org.example.coursegetter.entities.baseclasses;

import org.jetbrains.annotations.NotNull;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

public class ScheduleEntry implements Comparable<ScheduleEntry>, Serializable, IScheduleEntry {
    private final String assignedRoom1;  // room in the fall.

    @Override
    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String getMeetingCode() {
        return meetingCode;
    }

    public String getAssignedRoom1() {
        return Objects.requireNonNullElse(assignedRoom1, "");
    }

    public String getAssignedRoom2() {
        return Objects.requireNonNullElse(assignedRoom2, "");
    }

    // always null if the course
    // is an S course.
    private final String courseCode;
    private final String meetingCode;
    private final String assignedRoom2;  // room in the winter.
    private final String meetingStartTime;
    private final String meetingDay;
    private final String meetingEndTime;


    public ScheduleEntry(Map<String, Object> sInfo, String crsCode, String meetingCode) {
        this.assignedRoom1 = (String) sInfo.get("assignedRoom1");
        this.assignedRoom2 = (String) sInfo.get("assignedRoom2");
        this.meetingStartTime = (String) sInfo.get("meetingStartTime");
        this.meetingDay = (String) sInfo.get("meetingDay");
        this.meetingEndTime = (String) sInfo.get("meetingEndTime");
        this.courseCode = crsCode;
        this.meetingCode = meetingCode;
    }

    /**
     * Returns the start time of the meeting, or null if the
     * meeting is async.
     */
    public LocalTime getStartTime() {
        if (meetingStartTime == null)
            return null;
        return LocalTime.parse(meetingStartTime);
    }

    /**
     * Returns the end time of the meeting, or null
     * if the meeting is async.
     */
    public LocalTime getEndTime() {
        if (meetingEndTime == null)
            return null;
        return LocalTime.parse(meetingEndTime);
    }

    /**
     * Returns true if this schedule entry does
     * not have any time listed.
     * This is due to either the course being async
     * OR the course being an independent reading
     * or research opportunity program.
     */
    public boolean isAsync() {
        return this.meetingDay == null ||
                this.meetingStartTime == null ||
                this.meetingEndTime == null;
    }

    /**
     * Returns the day of the meeting as
     * a DayOfWeek enum, or null if the
     * meeting is async.
     */
    public DayOfWeek getDay() {
        if (meetingDay == null) return null;
        return switch (meetingDay) {
            case "MO" -> DayOfWeek.MONDAY;
            case "TU" -> DayOfWeek.TUESDAY;
            case "WE" -> DayOfWeek.WEDNESDAY;
            case "TH" -> DayOfWeek.THURSDAY;
            case "FR" -> DayOfWeek.FRIDAY;
            case "SA" -> DayOfWeek.SATURDAY;
            case "SU" -> DayOfWeek.SUNDAY;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return this.meetingDay + " " + this.meetingStartTime + "-" + this.meetingEndTime;
    }

    /**
     * @param o the other schedule entry to be compared.
     * @return -1, 0, 1 depending on which schedule entry
     * STARTS earlier or later.
     * Async schedule entries are assumed to start earlier than monday 12AM.
     */
    @Override
    public int compareTo(@NotNull ScheduleEntry o) {
        int asyncCode = dealWithAsync(o);
        if (asyncCode != 0) return asyncCode == 2 ? 0 : asyncCode;
        // step 1: compare dates

        int thisDay = this.getDay().getValue();
        int otherDay = o.getDay().getValue();
        if (thisDay < otherDay) return -1;
        else if (thisDay > otherDay) return 1;
        // step 2: eliminate null cases

        // we can now assert that both this and o are NOT async
        // the days are the same, then compare timings
        return this.getStartTime().compareTo(o.getStartTime());
    }

    private int dealWithAsync(ScheduleEntry o) {
        if (this.isAsync() && !o.isAsync()) {
            return -1;
        } else if (!this.isAsync() && o.isAsync()) {
            return 1;
        } else if (this.isAsync() && o.isAsync()) {
            return 2;
        } else return 0;
    }
}