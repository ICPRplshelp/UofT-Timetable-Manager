package org.revamped.usecases;

import org.example.coursegetter.entities.ScheduleEntry;
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

    /**
     * Checks course-related warnings for a set of planned and past courses.
     *
     * @param planned all planned courses and the only courses to check warnings for
     * @param passed the courses the student has taken in the past
     * @return a map mapping each applicable warning type to the courses that it affects.
     */
    public Map<WarningType, Set<String>> checkCourseWarnings(Set<String> planned, Set<String> passed){
        throw new RuntimeException();
    }


    /**
     * Check timetable-related warnings for a set of planned courses.
     *
     * @param planned a student's planned courses with lecture section info.
     * @return a map mapping each warning type to each ScheduleEntry
     * that would fall victim to the warning.
     */
    public Map<WarningType, Set<ScheduleEntry>> checkTimetableWarnings(Map<String, Set<String>> planned){
        throw new RuntimeException();
    }

}
