package org.phase2.studentrelated.usecases;

import org.example.timetable.entities.WarningType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequisiteWarningAdder {
    private final WarningChecker2 warningChecker2;

    public RequisiteWarningAdder(WarningChecker2 warningChecker2) {
        this.warningChecker2 = warningChecker2;
    }

    /**
     * Adds warnings to all courses - that is, requisite warnings.
     *
     * @param planned  planned courses with suffix
     * @param passed   passed courses without suffix
     * @param warnList warn list to add to
     */
    void addRequisiteWarnings(Set<String> planned, Set<String> passed, Map<String, Set<WarningType>> warnList) {
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
        checkRequisiteIssuesForAllCoursesGivenPlannedPassed(planned, passed, warnList, passedForS, concurrentF, concurrentSY);
    }

    void checkRequisiteIssuesForAllCoursesGivenPlannedPassed(Set<String> planned, Set<String> passed, Map<String, Set<WarningType>> warnList, Set<String> passedForS, Set<String> concurrentF, Set<String> concurrentSY) {
        for (String plannedCourse : planned) {
            CheckRequisite checker = new CheckRequisite(plannedCourse);

            String exclusion = warningChecker2.getPlannedSearcher().getCourse(plannedCourse).getExclusion();
            String coreq = warningChecker2.getPlannedSearcher().getCourse(plannedCourse).getCorequisite();
            String prereq = warningChecker2.getPlannedSearcher().getCourse(plannedCourse).getPrerequisite();

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
}
