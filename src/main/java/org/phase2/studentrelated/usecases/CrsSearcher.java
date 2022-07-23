package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.Course;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.Set;

public interface CrsSearcher {
    Course getCourse(String code);
    Set<String> getMeetings(String code);

    /**
     * Returns all schedule entries from the course.
     * Do NOT include async sections.
     *
     * @param code
     * @param meeting
     * @return
     */
    Set<IScheduleEntry> getScheduleEntries(String code, String meeting);
}
