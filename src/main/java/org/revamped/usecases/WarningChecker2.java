package org.revamped.usecases;

import org.example.timetable.entities.warningtypes.WarningType;
import org.revamped.entities.Student2;

import java.util.Map;
import java.util.Set;

public class WarningChecker2 {
    private final CrsSearcher plannedSearcher;
    private final CrsSearcher pastSearcher;

    public WarningChecker2(CrsSearcher plannedSearcher, CrsSearcher pastSearcher) {
        this.plannedSearcher = plannedSearcher;
        this.pastSearcher = pastSearcher;
    }

    public Map<String, Set<WarningType>> checkCourseWarnings(Set<String> planned, Set<String> passed){
        throw new RuntimeException();
    }

}
