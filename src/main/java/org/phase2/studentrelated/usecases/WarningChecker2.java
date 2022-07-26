package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.Course;
import org.example.requisitechecker.usecases.RequisiteChecker;
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
     * @return a map mapping each applicable warning type to the courses that it affects.
     */
    public Map<String, Set<WarningType>> checkCourseWarnings(Set<String> planned, Set<String> passed) {
        List<String> plannedCourses = new ArrayList<>(planned);
        List<String> plannedList = new ArrayList<>();

        for (String string : plannedCourses) {
            String substring = string.substring(0, string.length() - 2);
            plannedList.add(substring);}

        List<String> allList = new ArrayList<>();
        allList.addAll(plannedList); allList.addAll(passed);

        Map<String, Set<WarningType>> warnList = new HashMap<>();

        for (String plannedCourse : plannedCourses) {
            CheckRequisite checker = new CheckRequisite(plannedCourse);

            String exclusion = plannedSearcher.getCourse(plannedCourse).getExclusion();
            String coreq = plannedSearcher.getCourse(plannedCourse).getCorequisite();
            String prereq = plannedSearcher.getCourse(plannedCourse).getPrerequisite();

            checker.exclusionChecker(allList, exclusion, warnList);
            checker.prereqChecker(allList, prereq, warnList);
            checker.coreqChecker(plannedList, coreq, warnList);
        }

        return warnList;
        // throw new RuntimeException();
    }


    /**
     * Check timetable-related warnings for a set of planned courses.
     *
     * @param planned a student's planned courses with lecture section info.
     * @return a map mapping each warning type to each ScheduleEntry
     * that would fall victim to the warning.
     */
    public Map<IScheduleEntry, Set<WarningType>> checkTimetableWarnings(Map<String, Set<String>> planned) {
        Set<IScheduleEntry> allScheduleEntries = generateScheduleEntriesAll(planned);
        for (String crs : planned.keySet()) {
            Set<String> meetings = planned.get(crs);
            for (String meeting : meetings) {
                Set<IScheduleEntry> scheduleEntrySet = this.plannedSearcher.getScheduleEntries(crs, meeting);
                for(IScheduleEntry se : scheduleEntrySet){
                    // do something. for example, when checking conflicts,
                    // do checkConflict(se, allScheduleEntries)
                    // and if it does conflict, let us know.

                    // the same applies to distance checks.
                }
            }
        }

        return Collections.emptyMap();
    }

    /**
     * Generates all schedule entries of the planned courses and its lecture sections
     * passed in.
     *
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
