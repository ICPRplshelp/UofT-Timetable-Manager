package org.example.coursegetter.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

public class ScheduleEntry implements Comparable<ScheduleEntry>, Serializable {
    private final String assignedRoom1;  // room in the fall.

    public String getAssignedRoom1() {
        return assignedRoom1;
    }

    private final String meetingStartTime;
    private final String meetingDay;
    private final String meetingEndTime;


    public ScheduleEntry(Map<String, Object> sInfo) {
        this.assignedRoom1 = (String) sInfo.get("assignedRoom1");
        this.assignedRoom2 = (String) sInfo.get("assignedRoom2");
        this.meetingStartTime = (String) sInfo.get("meetingStartTime");
        this.meetingDay = (String) sInfo.get("meetingDay");
        this.meetingEndTime = (String) sInfo.get("meetingEndTime");
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
    public int compareTo(ScheduleEntry o) {
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
