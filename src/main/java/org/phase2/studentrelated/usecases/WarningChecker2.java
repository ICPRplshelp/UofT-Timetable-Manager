package org.phase2.studentrelated.usecases;

import org.example.requisitechecker.courselocationtracker.usecases.BuildingComparator;
import org.example.requisitechecker.courselocationtracker.usecases.BuildingStorageConstructor;
import org.example.timetable.entities.WarningType;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The warning checker does not need to pinpoint the courses
 * that are causing such problems.
 */
public class WarningChecker2 {
    private final RequisiteWarningAdder requisiteWarningAdder = new RequisiteWarningAdder(this);

    public UsableCourseSearcher getPlannedSearcher() {
        return plannedSearcher;
    }

    private final UsableCourseSearcher plannedSearcher;
    private final UsableCourseSearcherPrev pastSearcher;
    private final Map<String, Set<String>> planned;
    private final Set<String> passed;

    private final BuildingStorageConstructor buildingStorageConstructor = new BuildingStorageConstructor();
    private final BuildingComparator buildingComparator = new BuildingComparator(buildingStorageConstructor.makeAllBuildings());

    public WarningChecker2(UsableCourseSearcher plannedSearcher, UsableCourseSearcherPrev pastSearcher,
                           Map<String, Set<String>> planned,
                           Set<String> passed) {
        this.plannedSearcher = plannedSearcher;
        this.pastSearcher = pastSearcher;
        this.planned = planned;
        this.passed = passed;
    }

    /**
     * Checks course-related warnings for a set of planned and past courses.
     * Generally, course-related warnings should not know when
     * lectures are timed.
     * Meaning whatever is returned, no conflict warnings should be
     * put here.
     * Conflict warnings should be reserved for schedule-related warnings,
     * which can be found in a more below method.
     *
     * @return a map mapping each applicable course (with the -F/-Y/-S) to the set of warnings that may affect it.
     */
    public Map<String, Set<WarningType>> checkCourseWarnings() {
        Map<String, Set<WarningType>> warnList = new HashMap<>();
        Set<String> planned1 = planned.keySet();
        addCourseWarningsHelper(warnList, planned1);
        return warnList;
    }

    /**
     * Helper method for course-related warnings.
     * These warnings are based on the set of planned-passed courses.
     *
     * @param warnList the warnings list to be passed in
     * @param planned1 modified planned course list
     */
    private void addCourseWarningsHelper(Map<String, Set<WarningType>> warnList, Set<String> planned1) {
        requisiteWarningAdder.addRequisiteWarnings(planned1, passed, warnList);
    }

    /**
     * This overload adds otherCourse to the courses that you are planning to take.
     *
     * @param otherCourse the other course to add
     * @return any issues
     */
    public Map<String, Set<WarningType>> checkCourseWarnings(String otherCourse){
        Map<String, Set<WarningType>> warnList = new HashMap<>();
        Set<String> planned0102 = new HashSet<>(planned.keySet());
        planned0102.add(otherCourse);
        addCourseWarningsHelper(warnList, planned0102);
        return warnList;

    }

    private Map<IScheduleEntry, Set<WarningType>> lastMap = new HashMap<>();

    /**
     * Returns the latest map generated by this.checkTimetableWarnings().
     * @return ^
     */
    public Map<IScheduleEntry, Set<WarningType>> getLastMap() {
        return lastMap;
    }

    /**
     * Check timetable-related warnings for a set of planned courses.
     *
     * @return a map mapping each ScheduleEntry to the Warnings it may have.
     */
    public Map<IScheduleEntry, Set<WarningType>> checkTimetableWarnings() {
        Map<IScheduleEntry, Set<WarningType>> warningMap = new HashMap<>();
        Set<IScheduleEntry> allScheduleEntries = generateScheduleEntriesAll(planned);
        for (IScheduleEntry se : allScheduleEntries) {
            if (checkConflict(se, allScheduleEntries)) {
                if (!warningMap.containsKey(se)) {
                    warningMap.put(se, new HashSet<>());
                }
                warningMap.get(se).add(WarningType.CONFLICT);
            }
            if (checkBackToBack(se, allScheduleEntries)) {
                if (!warningMap.containsKey(se)) {
                    warningMap.put(se, new HashSet<>());
                }
                warningMap.get(se).add(WarningType.DIST);
            }
        }
        this.lastMap = warningMap;
        return warningMap;
    }

    private boolean checkConflict(IScheduleEntry se, Set<IScheduleEntry> allScheduleEntries) {
        for (IScheduleEntry se2 : allScheduleEntries) {
            if (se == se2 || !se.getDay().equals(se2.getDay())) {
                continue;
            }

            // case 1: se startTime <= se2 startTime < se endTime
            if (se.getStartTime().compareTo(se2.getStartTime()) <= 0 && se2.getStartTime().isBefore(se.getEndTime())) {
                return true;
            }

            // case 2: se startTime < se2 endTime <= se endTime
            if (se.getStartTime().isBefore(se2.getStartTime()) && se2.getStartTime().compareTo(se.getEndTime()) <= 0) {
                return true;
            }

        }
        return false;
    }

    private boolean checkBackToBack(IScheduleEntry se, Set<IScheduleEntry> allScheduleEntries) {
        for (IScheduleEntry se2 : allScheduleEntries) {
            if (se == se2 || !se.getDay().equals(se2.getDay())) {
                continue;
            }

            // case 1: se startTime <= se2 startTime < se endTime
            if (se.getStartTime().compareTo(se2.getEndTime()) == 0) {
                return checkDistance(se, se2);
            }

            // case 2: se startTime < se2 endTime <= se endTime
            if (se2.getStartTime().compareTo(se.getEndTime()) == 0) {
                return checkDistance(se, se2);
            }

        }
        return false;
    }

    private boolean checkDistance(IScheduleEntry se, IScheduleEntry se2) {
        if (!se.getAssignedRoom1().equals("") && !se2.getAssignedRoom1().equals("")) {
            return distanceJudgeHelper(buildingComparator.getDistance(se.getAssignedRoom1(), se2.getAssignedRoom1()));
        } else if (!se.getAssignedRoom2().equals("") && !se2.getAssignedRoom2().equals("")) {
            return distanceJudgeHelper(buildingComparator.getDistance(se.getAssignedRoom2(), se2.getAssignedRoom2()));
        }
        return false;
    }

    private boolean distanceJudgeHelper(double distance) {
        double timeNeeded = (distance/2.0) * 60.0;
        return timeNeeded > 11.0;
    }
    /**
     * Generates all schedule entries of the planned courses and its lecture sections
     * passed in.
     * <p>
     * Do not modify this method.
     *
     * @param planned same format in the Student class - planned courses
     * @return what was generated
     */
    private Set<IScheduleEntry> generateScheduleEntriesAll(Map<String, Set<String>> planned) {
        Set<IScheduleEntry> scheduleEntries = new HashSet<>();
        for (String crs : planned.keySet()) {
            Set<String> meetings = planned.get(crs);
            for (String meeting : meetings) {
                scheduleEntries.addAll(this.plannedSearcher.getScheduleEntries(crs, meeting));
            }
        }
        return scheduleEntries;
    }
}
