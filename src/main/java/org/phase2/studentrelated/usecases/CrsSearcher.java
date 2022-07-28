package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.baseclasses.Course;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.Set;

public interface CrsSearcher {

    /**
     * Gets the Course object from code.
     * @param code e.g. CSC110Y1-F (must include -F/-S/-Y suffix)
     * @return the course if it exists, or null otherwise.
     */
    Course getCourse(String code);


    /**
     * Get the course meetings from the code.
     * @param code e.g. CSC110Y1-F (must include -F/-S/-Y suffix)
     * @return the set of meetings if it exists, or null otherwise.
     */
    Set<String> getMeetings(String code);

    /**
     * Returns all schedule entries from the course.
     * Do NOT include async sections.
     *
     * @param code the course code
     * @param meeting the meeting
     * @return the schedule entries, if applicable
     */
    Set<IScheduleEntry> getScheduleEntries(String code, String meeting);
}
