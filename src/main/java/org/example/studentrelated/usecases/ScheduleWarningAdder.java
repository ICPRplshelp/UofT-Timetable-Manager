package org.example.studentrelated.usecases;

import org.example.timetable.entities.WarningType;
import org.example.studentrelated.presenters.IScheduleEntry;

import java.util.Map;
import java.util.Set;

public interface ScheduleWarningAdder {

    /**
     * Adds all warnings based on what this warning checker can do
     * @param allScheduleEntries all schedule entries the student signed up for
     * @param warningMap the map of warnings that is to be modified
     */
    void addWarnings(Set<IScheduleEntry> allScheduleEntries, Map<IScheduleEntry, Set<WarningType>> warningMap);
}
