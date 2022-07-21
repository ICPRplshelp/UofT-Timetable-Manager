package org.example.enrollmentissues.entities.internal;

import org.example.coursegetter.entities.Course;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class representing a scheduling issue.
 * Likely due to timetable conflicts or far distances.
 * <p>
 * Schedule issues target issues that only apply to a specific meeting
 * day.
 * For example, if Monday meetings are okay but Wednesday meetings
 * have issues, please use this class.
 */
public abstract class ScheduleIssue {




    private final Collection<ScheduleEntryIdentifier> meetingVictims;
    private final Collection<ScheduleEntryIdentifier> meetingCulprits;
    private final Map<ScheduleEntryIdentifier, Collection<ScheduleEntryIdentifier>> victimsAndCausers;

    /**
     * Initializes this class.
     * @param victimsAndCausers affectScheduleEntries : scheduleEntriesCausingIt
     */
    public ScheduleIssue(Map<ScheduleEntryIdentifier, Collection<ScheduleEntryIdentifier>> victimsAndCausers){
        this.victimsAndCausers = victimsAndCausers;
        this.meetingVictims = victimsAndCausers.keySet();
        Set<ScheduleEntryIdentifier> temp = new HashSet<>();
        victimsAndCausers.values().forEach(temp::addAll);
        this.meetingCulprits = temp;  // a list of meetings that are causing all of this
    }


    /**
     * @return A collection of Meeting codes (LEC0101) that
     * have the problems.
     */
    public Collection<String> getMeetingProblems() {
        Collection<ScheduleEntryIdentifier> schProblems = getCellVictims();
        Set<String> meetings = new TreeSet<>();
        for (ScheduleEntryIdentifier sei : schProblems) {
            meetings.add(sei.meeting());
        }
        return meetings;
    }

    /**
     * @return A collection of ScheduleEntry objects of this course that is victim to the problem.
     */
    public Collection<ScheduleEntryIdentifier> getCellVictims(){
        return meetingVictims;
    }

    /**
     * Example: If CSC258's L0201 WE 11:00-12:00's meeting is causing this
     * problem, return its respective schedule entry.
     * <p>
     * If an entire course is causing the issue, do not return
     * anything here.
     *
     * @return A collection of timetable cells that are causing this problem.
     */
    public Collection<ScheduleEntryIdentifier> getCellCulprits(){
        return meetingCulprits;
    }


}
