package org.example.entities.courserelated;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

public class ScheduleEntry {
    public final String assignedRoom1;
    public final String assignedRoom2;
    public final String meetingStartTime;
    public final String meetingDay;
    public final String meetingEndTime;

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
    public boolean isAsync(){
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
        switch(meetingDay) {
            case "MO":
                return DayOfWeek.MONDAY;
            case "TU":
                return DayOfWeek.TUESDAY;
            case "WE":
                return DayOfWeek.WEDNESDAY;
            case "TH":
                return DayOfWeek.THURSDAY;
            case "FR":
                return DayOfWeek.FRIDAY;
            case "SA":
                return DayOfWeek.SATURDAY;
            case "SU":
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }
}
