package org.example.coursegetter.entities;

import java.util.*;

public class Meeting {
    public String getCancel() {
        return cancel;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public String getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public String getActualWaitlist() {
        return actualWaitlist;
    }

    public String getEnrollmentIndicator() {
        return enrollmentIndicator;
    }

    public String getActualEnrolment() {
        return actualEnrolment;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getWaitlist() {
        return waitlist;
    }

    public String getOnline() {
        return online;
    }

    public List<EnrollmentControl> getEnrollmentControls() {
        return enrollmentControls;
    }

    private final String cancel;
    private final String sectionNumber;
    private final String enrollmentCapacity;
    private final String teachingMethod;
    private final String actualWaitlist;
    private final String enrollmentIndicator;
    private final String actualEnrolment;
    private final String subtitle;
    private final String waitlist;
    private final String online;
    private final Set<ScheduleEntry> scheduleEntryList = new TreeSet<>();
    private final List<EnrollmentControl> enrollmentControls;

    public Meeting(Map<String, Object> mInfo) {
        this.cancel = (String) mInfo.get("cancel");
        this.sectionNumber = (String) mInfo.get("sectionNumber");
        this.enrollmentCapacity = (String) mInfo.get("enrollmentCapacity");
        this.teachingMethod = (String) mInfo.get("teachingMethod");
        this.actualWaitlist = (String) mInfo.get("actualWaitlist");
        this.enrollmentIndicator = (String) mInfo.get("enrollmentIndicator");
        this.actualEnrolment = (String) mInfo.get("actualEnrolment");
        this.subtitle = (String) mInfo.get("subtitle");
        this.waitlist = (String) mInfo.get("waitlist");
        this.online = (String) mInfo.get("online");

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
