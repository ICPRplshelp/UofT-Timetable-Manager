package org.phase2.studentrelated.usecases;

import org.example.timetable.entities.WarningType;
import org.phase2.studentrelated.presenters.IScheduleEntry;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The warning checker does not need to pinpoint the courses
 * that are causing such problems.
 */
public class WarningChecker2 {
    private final WarningAdder requisiteWarningAdder = new RequisiteWarningAdder();
    private final WarningAdder checkTaken = new TakenWarningAdder();
    private final Map<String, Set<String>> planned;
    private final Set<String> passed;
    private final WarningAdder fyfWarningAdder = new FYFWarningAdder();
    private final ConflictChecker conflictChecker = new ConflictChecker();
    private final DistanceChecker distanceChecker = new DistanceChecker();
    private final MissingLecAdder missingLecAdder;
    private final UsableCourseSearcher plannedSearcher;
    private Map<IScheduleEntry, Set<WarningType>> lastMap = new HashMap<>();

    public WarningChecker2(UsableCourseSearcher plannedSearcher,
                           Map<String, Set<String>> planned,
                           Set<String> passed) {
        this.missingLecAdder = new MissingLecAdder(plannedSearcher, planned);
        this.passed = passed;
        this.plannedSearcher = plannedSearcher;
        this.planned = planned;
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
        this.checkTimetableWarnings();
        Map<String, Set<WarningType>> warnList2 = condenseScheduleWarnings();
        combineBothWarnLists(warnList, warnList2);

        return warnList;
    }

    private void combineBothWarnLists(Map<String, Set<WarningType>> warnList, Map<String, Set<WarningType>> warnList2) {
        for(Map.Entry<String, Set<WarningType>> entry : warnList2.entrySet()){
            if(warnList.containsKey(entry.getKey())){
                warnList.get(entry.getKey()).addAll(entry.getValue());
            }else{
                warnList.put(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * Helper method for course-related warnings.
     * These warnings are based on the set of planned-passed courses.
     *
     * @param warnList the warnings list to be passed in
     * @param planned1 modified planned course list
     */
    private void addCourseWarningsHelper(Map<String, Set<WarningType>> warnList, Set<String> planned1) {
        requisiteWarningAdder.addWarnings(planned1, passed, warnList);
        fyfWarningAdder.addWarnings(planned1, passed, warnList);
        missingLecAdder.addWarnings(planned1, passed, warnList);
        checkTaken.addWarnings(planned1, passed, warnList);
    }

    /**
     * This overload adds otherCourse to the courses that you are planning to take.
     *
     * @param otherCourse the other course to add
     * @return any issues
     */
    public Map<String, Set<WarningType>> checkCourseWarnings(String otherCourse) {
        Map<String, Set<WarningType>> warnList = new HashMap<>();
        Set<String> planned0102 = new HashSet<>(planned.keySet());
        planned0102.add(otherCourse);
        addCourseWarningsHelper(warnList, planned0102);
        return warnList;

    }

    /**
     * Once SE-related checks are done, condense this into course
     * If one SE has the warning then the entire course has the warning
     * related warnings format
     * @return check the description
     */
    private Map<String, Set<WarningType>> condenseScheduleWarnings(){

        Map<String, Set<WarningType>> soFar = new HashMap<>();
        for(IScheduleEntry ise : getLastMap().keySet()){
            Set<WarningType> swt = getLastMap().get(ise);
            String cc = ise.getCourseCode();
            if(!soFar.containsKey(cc)){
                soFar.put(cc, swt);
            }
            else {
                Set<WarningType> curWarns = soFar.get(cc);
                Set<WarningType> newWarns = new HashSet<>(curWarns);
                newWarns.addAll(swt);
                soFar.put(cc, newWarns);
            }
        }
        return soFar;
    }

    /**
     * Returns the latest map generated by this.checkTimetableWarnings().
     *
     * @return ^
     */
    public Map<IScheduleEntry, Set<WarningType>> getLastMap() {
        return lastMap;
    }

    /**
     * Check timetable-related warnings for a set of planned courses.
     *
     * Look at the method above to grab its warnings.
     */
    public void checkTimetableWarnings() {
        Map<IScheduleEntry, Set<WarningType>> warningMap = new HashMap<>();
        Set<IScheduleEntry> allScheduleEntries = generateScheduleEntriesAll(planned);
        for (IScheduleEntry se : allScheduleEntries) {
            if (conflictChecker.checkConflict(se, allScheduleEntries)) {
                if (!warningMap.containsKey(se)) {
                    warningMap.put(se, new HashSet<>());
                }
                warningMap.get(se).add(WarningType.CONFLICT);
            }
            if (distanceChecker.checkBackToBack(se, allScheduleEntries)) {
                if (!warningMap.containsKey(se)) {
                    warningMap.put(se, new HashSet<>());
                }
                warningMap.get(se).add(WarningType.DIST);
            }
        }
        this.lastMap = warningMap;
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
                scheduleEntries.addAll(plannedSearcher.getScheduleEntries(crs, meeting));
            }
        }
        return scheduleEntries;
    }
}
