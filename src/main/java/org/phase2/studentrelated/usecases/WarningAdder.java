package org.phase2.studentrelated.usecases;

import org.example.timetable.entities.WarningType;

import java.util.Map;
import java.util.Set;

public interface WarningAdder {
    void addWarnings(Set<String> planned, Set<String> passed,
                     Map<String, Set<WarningType>> warnList);
}
