package org.example.coursegetter.entities;

import java.io.Serializable;
import java.util.*;

public class Meeting implements Serializable {

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public String getEnrollmentIndicator() {
        return enrollmentIndicator;
    }

    public List<EnrollmentControl> getEnrollmentControls() {
        return enrollmentControls;
    }

    private final String sectionNumber;
    private final String teachingMethod;
    private final String enrollmentIndicator;
    private final Set<ScheduleEntry> scheduleEntryList = new TreeSet<>();
    private final List<EnrollmentControl> enrollmentControls;

    public Meeting(Map<String, Object> mInfo) {
        this.sectionNumber = (String) mInfo.get("sectionNumber");
        this.teachingMethod = (String) mInfo.get("teachingMethod");
        this.enrollmentIndicator = (String) mInfo.get("enrollmentIndicator");

        // System.out.println(mInfo.get("schedule"));
        Object tempSchedule = mInfo.get("schedule");
        Map<String, Object> tempScheduleMap;
        if (tempSchedule instanceof Map) {
            tempScheduleMap = ((Map<String, Object>) mInfo.get("schedule"));
            for (var tse : tempScheduleMap.values()) {
                ScheduleEntry se;
                if (tse instanceof Map) {
                    se = new ScheduleEntry((Map<String, Object>) tse);
                    scheduleEntryList.add(se);
                }
            }
        }
        this.enrollmentControls = new ArrayList<>();
        List<Map<String, String>> tempEnrollmentControl = (List<Map<String, String>>) mInfo.get("enrollmentControls");
        if (tempEnrollmentControl != null)
            tempEnrollmentControl.forEach(ec ->
                    this.enrollmentControls.add(new EnrollmentControl(ec)));
    }

    /**
     * Returns the schedule entries of the course.
     * That is, the list of times you have to actually go there.
     *
     * @return the schedule entries of this course, sorted by the day
     * and time of the week.
     */
    public Set<ScheduleEntry> getScheduleEntries() {
        return Collections.unmodifiableSet(scheduleEntryList);
    }

    @Override
    public String toString() {
        return this.teachingMethod + this.sectionNumber;
    }
}
