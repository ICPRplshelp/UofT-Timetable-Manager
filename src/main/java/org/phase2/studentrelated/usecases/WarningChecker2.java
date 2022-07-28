package org.phase2.studentrelated.usecases;

import org.example.timetable.entities.warningtypes.WarningType;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.*;

/**
 * The warning checker does not need to pinpoint the courses
 * that are causing such problems.
 */
public class WarningChecker2 {
    private final CourseSearchAdapter plannedSearcher;
    private final CourseSearchAdapterPrev pastSearcher;

    public WarningChecker2(CourseSearchAdapter plannedSearcher, CourseSearchAdapterPrev pastSearcher) {
        this.plannedSearcher = plannedSearcher;
        this.pastSearcher = pastSearcher;
    }

    /**
     * Checks course-related warnings for a set of planned and past courses.
     *
     * @param planned all planned courses and the only courses to check warnings for
     * @param passed  the courses the student has taken in the past
     * @return a map mapping each applicable course (with the -F/-Y/-S) to the set of warnings that may affect it.
     */
    public Map<String, Set<WarningType>> checkCourseWarnings(Set<String> planned, Set<String> passed) {
        Map<String, Set<WarningType>> warnList = new HashMap<>();
        addRequisiteWarnings(planned, passed, warnList);

        return warnList;
    }

    /**
     * Adds warnings to all courses - that is, requisite warnings.
     * @param planned planned courses with suffix
     * @param passed passed courses without suffix
     * @param warnList warn list to add to
     */
    private void addRequisiteWarnings(Set<String> planned, Set<String> passed, Map<String, Set<WarningType>> warnList) {
        Set<String> plannedF = new HashSet<>();
        Set<String> plannedS = new HashSet<>();
        Set<String> plannedY = new HashSet<>();
        for (String pCrs : planned) {
            String pCrsNoSuffix = pCrs.substring(0, pCrs.length() - 2);
            char section = pCrs.charAt(pCrs.length() - 1);
            switch (section) {
                case 'F' -> plannedF.add(pCrsNoSuffix);
                case 'S' -> plannedS.add(pCrsNoSuffix);
                case 'Y' -> plannedY.add(pCrsNoSuffix);
            }
        }
        Set<String> passedForS = new HashSet<>(passed);
        passedForS.addAll(plannedF);
        Set<String> concurrentF = new HashSet<>(passed);
        concurrentF.addAll(plannedF);
        concurrentF.addAll(plannedY);
        Set<String> concurrentSY = new HashSet<>(concurrentF);
        concurrentSY.addAll(plannedS);
        checkRequisiteIssuesForALlCoursesGivenPlannedPassed(planned, passed, warnList, passedForS, concurrentF, concurrentSY);
    }

    private void checkRequisiteIssuesForALlCoursesGivenPlannedPassed(Set<String> planned, Set<String> passed, Map<String, Set<WarningType>> warnList, Set<String> passedForS, Set<String> concurrentF, Set<String> concurrentSY) {
        for (String plannedCourse : planned) {
            CheckRequisite checker = new CheckRequisite(plannedCourse);

            String exclusion = plannedSearcher.getCourse(plannedCourse).getExclusion();
            String coreq = plannedSearcher.getCourse(plannedCourse).getCorequisite();
            String prereq = plannedSearcher.getCourse(plannedCourse).getPrerequisite();

            char sectionOfCourse = plannedCourse.charAt(plannedCourse.length() - 1);
            // these sets may not have the suffix, -F/-Y/-S
            Set<String> concurrentOrPassedCourses;
            if (sectionOfCourse == 'F') {
                concurrentOrPassedCourses = concurrentF;
            } else {
                concurrentOrPassedCourses = concurrentSY;
            }
            Set<String> passedCoursesOnly;
            if (sectionOfCourse == 'S') {
                passedCoursesOnly = passedForS;
            } else passedCoursesOnly = passed;

            checker.exclusionChecker(concurrentOrPassedCourses, exclusion, warnList);
            checker.prereqChecker(passedCoursesOnly, prereq, warnList);
            checker.coreqChecker(concurrentOrPassedCourses, coreq, warnList);
        }
    }


    /**
     * Check timetable-related warnings for a set of planned courses.
     *
     * @param planned a student's planned courses with lecture section info.
     * @return a map mapping each ScheduleEntry to the Warnings it may have.
     */
    public Map<IScheduleEntry, Set<WarningType>> checkTimetableWarnings(Map<String, Set<String>> planned) {
        Map<IScheduleEntry, Set<WarningType>> warningMap = new HashMap<>();
        Set<IScheduleEntry> allScheduleEntries = generateScheduleEntriesAll(planned);
        for(IScheduleEntry se : allScheduleEntries){
            if (checkConflict(se, allScheduleEntries)){
                if (!warningMap.containsKey(se)){
                    warningMap.put(se, new HashSet<>());
                }
                warningMap.get(se).add(WarningType.CONFLICT);
            }
            // add distance checks here
        }

        return warningMap;
    }

    private boolean checkConflict(IScheduleEntry se, Set<IScheduleEntry> allScheduleEntries){
        for (IScheduleEntry se2: allScheduleEntries){
            if (se == se2 || !se.getDay().equals(se2.getDay())){
                continue;
            }

            // case 1: se startTime <= se2 startTime < se endTime
            if (se.getStartTime().compareTo(se2.getStartTime()) <= 0 && se2.getStartTime().isBefore(se.getEndTime())){
                return true;
            }

            // case 2: se startTime < se2 endTime <= se endTime
            if (se.getStartTime().isBefore(se2.getStartTime()) && se2.getStartTime().compareTo(se.getEndTime()) <= 0){
                return true;
            }

        }
        return false;
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
