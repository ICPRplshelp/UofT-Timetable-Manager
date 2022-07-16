package org.example.enrollmentissues.entities.internal;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class representing a scheduling issue.
 * Likely due to timetable conflicts or far distances.
 *
 * Schedule issues target issues that only apply to a specific meeting
 * day.
 * For example, if Monday meetings are okay but Wednesday meetings
 * have issues, please use this class.
 */
public abstract class ScheduleIssue extends Issue {
    public ScheduleIssue(String course) {
        super(course);
    }


    /**
     * @return A collection of Meeting codes (LEC0101) that
     * have the problems.
     */
    public Collection<String> getMeetingProblems() {
        Collection<ScheduleEntryIdentifier> schProblems = getCellProblems();
        Set<String> meetings = new TreeSet<>();
        for (ScheduleEntryIdentifier sei : schProblems) {
            meetings.add(sei.meeting());
        }
        return meetings;
    }

    /**
     * @return A collection of ScheduleEntry objects causing the problem.
     */
    public abstract Collection<ScheduleEntryIdentifier> getCellProblems();

    /**
     * Example: If CSC258's L0201 WE 11:00-12:00's meeting is causing this
     * problem, return its respective schedule entry.
     * <p>
     * If an entire course is causing the issue, do not return
     * anything here.
     *
     * @return A collection of timetable cells that are causing this problem.
     */
    public abstract Collection<ScheduleEntryIdentifier> getCellCausers();


}
