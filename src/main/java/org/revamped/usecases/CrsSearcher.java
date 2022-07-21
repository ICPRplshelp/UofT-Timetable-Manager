package org.revamped.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.ScheduleEntry;

import java.util.Set;

public interface CrsSearcher {
    Course getCourse(String code);
    Set<String> getMeetings(String code);
    Set<ScheduleEntry> getScheduleEntries(String code, String meeting);
}
