package org.phase2.studentrelated.presenters;

import org.example.timetable.entities.WarningType;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.*;

public class StudentPresenter {
    private final WarningChecker2 warningChecker;
    private final StudentManager sm;
    private final WarningPresenter wp = new WarningPresenter();


    public StudentPresenter(WarningChecker2 warningChecker, StudentManager sm) {
        this.warningChecker = warningChecker;
        this.sm = sm;
    }

    /**
     * Tell me, what are the planned courses?
     */
    public void plannedStr() {
        System.out.println("PLANNED COURSES:");
    }

    /**
     * Print to the screen, passed courses (literally)
     */
    public void passedStr() {
        System.out.println("PASSED COURSES:");
    }

    /**
     * Generates a list of course information for a student's planned courses.
     * Example:
     * ["CSC110Y1-F LEC0101 TUT0202 FYF EXCL PRQ",
     * "MAT257Y1Y LEC0101 TUT0101 PRQ CRQ EXCL"]
     * This may be used to construct an HTML enumerable.
     *
     * @return check the description.
     */
    public Collection<String> getPlannedCourseInfo() {

        Map<String, Set<String>> planned = sm.getPlannedCourses();
        Set<String> passed = sm.getPassedCourses();
        Map<String, Set<WarningType>> cw = warningChecker.checkCourseWarnings(planned.keySet(), passed);
        List<String> soFar = new ArrayList<>();
        for (String pCrs : planned.keySet()) {
            Set<String> lecs = planned.get(pCrs);
            // join the above set into a string seperated by spaces
            String lecsStr = String.join(" ", lecs);
            String warnStr = "";
            if (cw.containsKey(pCrs)) {
                Set<WarningType> courseWarningSet = cw.get(pCrs);
                // join the above set into a string seperated by spaces using the .toString() method
                warnStr = wp.getWarningsAsString(courseWarningSet);
            }
            soFar.add(String.join(" ", List.of(pCrs, lecsStr, warnStr)).trim());
        }
        return soFar;
    }

    /**
     * Generates information for courses a student has passed,
     * trimming the -F/-Y/-S suffix from the courses if they
     * existed.
     *
     * @return check the description. This returns a list,
     * helpful if you want to generate an HTML list.
     * The list is sorted.
     */
    public Collection<String> getPassedCourseInfo() {
        return sm.getPassedCourses();
    }
}
