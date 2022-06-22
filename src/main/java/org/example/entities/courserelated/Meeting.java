package org.example.entities.courserelated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Meeting {
    public final String cancel;
    public final String sectionNumber;
    public final String enrollmentCapacity;
    public final String teachingMethod;
    public final String actualWaitlist;
    public final String enrollmentIndicator;
    public final String actualEnrolment;
    public final String subtitle;
    public final String waitlist;
    public final String online;
    public final List<ScheduleEntry> scheduleEntryList = new ArrayList<>();

    public Meeting(Map<String, Object> mInfo){
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
        Map<String, Object> tempScheduleMap = ((Map<String, Object>) mInfo.get("schedule"));
        for (var tse : tempScheduleMap.values()){
            ScheduleEntry se = new ScheduleEntry((Map<String, Object>) tse);
            scheduleEntryList.add(se);
        }
    }
}
