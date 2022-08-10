package org.example.studentrelated.usecases;

import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.timetable.entities.WarningType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckRequisite {
    private final String courseCode;
    private final RequisiteChecker checker;

    CheckRequisite(String code) {
        checker = new RequisiteChecker();
        courseCode = code;
    }

    /**
     * Checks if there are prerequisite warnings for a list of all courses, updates warnList if there is
     *
     * @param allCourses all courses to check prerequisite warnings for
     * @param requisite  the prerequisite as the string.
     * @param warnList   the list to update if there is a warning
     */

    public void prereqChecker(Set<String> allCourses, String requisite, Map<String, Set<WarningType>> warnList) {
        if (!checker.check(allCourses, requisite)) {
            if (!warnList.containsKey(courseCode)) {
                Set<WarningType> warning = new HashSet<>();
                warning.add(WarningType.PRQ);
                warnList.put(courseCode, warning);
            } else {
                warnList.get(courseCode).add(WarningType.PRQ);
            }
        }
    }

    /**
     * Checks if there are corequisite warnings for a list of planned courses, updates warnList if there is
     *
     * @param plannedCourses courses to check corequisite warnings for
     * @param requisite      the corequisite as the string.
     * @param warnList       the list to update if there is a warning
     */

    public void coreqChecker(Set<String> plannedCourses, String requisite, Map<String, Set<WarningType>> warnList) {
        if (!checker.check(plannedCourses, requisite)) {
            if (!warnList.containsKey(courseCode)) {
                Set<WarningType> warning = new HashSet<>();
                warning.add(WarningType.CRQ);
                warnList.put(courseCode, warning);
            } else {
                warnList.get(courseCode).add(WarningType.CRQ);
            }
        }
    }

    /**
     * Checks if there are exclusion warnings for a list of courses, updates warnList if there is
     *
     * @param allCourses all courses to check exclusion warnings for
     * @param Exclusion  the exclusion as the string.
     * @param warnList   the list to update if there is a warning
     */

    public void exclusionChecker(Set<String> allCourses, String Exclusion, Map<String, Set<WarningType>> warnList) {
        if (checker.checkExclusions(allCourses, Exclusion)) {
            if (!warnList.containsKey(courseCode)) {
                Set<WarningType> warning = new HashSet<>();
                warning.add(WarningType.EXC);
                warnList.put(courseCode, warning);
            } else {
                warnList.get(courseCode).add(WarningType.EXC);
            }
        }
    }

}
