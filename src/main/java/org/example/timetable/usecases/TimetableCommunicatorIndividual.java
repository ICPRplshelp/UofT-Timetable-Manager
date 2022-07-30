package org.example.timetable.usecases;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.CourseWarning;
import org.example.timetable.entities.Timetable;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;

import java.util.*;

/**
 * Communicates things relevant in Timetable to the controller
 * by only returning builtins and primitives.
 */
public class TimetableCommunicatorIndividual {
    private final Timetable timetable;
    private final CourseChoice cc;

    /**
     * Constructs TimetableCommunicatorIndividual.
     *
     * @param timetable the timetable of the associated student.
     * @param cc        a courseChoice within the timetable.
     */
    public TimetableCommunicatorIndividual(Timetable timetable, CourseChoice cc) {
        this.timetable = timetable;
        this.cc = cc;
    }

    /**
     * Returns the course code of the courseChoice.
     *
     * @return ^
     */
    public String getCourseCode() {
        return cc.getCourse().getOfferingCode();
    }

    /**
     * Returns the warning types associated with the courseChoice.
     *
     * @return ^
     */
    public List<WarningType> getWarningTypesList() {
        List<WarningType> soFar = new ArrayList<>();
        Set<TimetableWarning> allWarns = getAllWarnsFromCourseChoice();
        for (TimetableWarning ttw : allWarns) {
            soFar.add(ttw.getWarningType());
        }
        return soFar;
    }

    /**
     * Returns the warnings associated with the courseChoice.
     *
     * @return ^
     */
    private Set<TimetableWarning> getAllWarnsFromCourseChoice() {
        CourseWarning courseWarning = timetable.getWarning(cc);
        if (courseWarning == null) return new HashSet<>();
        return courseWarning.getAllWarnings();
    }

    /**
     * Returns the sections of the courseChoice.
     *
     * @return ^
     */
    public List<String> getSectionsFromCourse() {
        List<String> outputSoFar = new ArrayList<>();
        if (Objects.isNull(cc.getLectureSection())) {
            if (!cc.checkLec()) outputSoFar.add("LEC????");
        } else {
            outputSoFar.add(cc.getLectureSection());
        }

        if (Objects.isNull(cc.getTutSection())) {
            if (!cc.checkTut()) outputSoFar.add("TUT????");
        } else {
            outputSoFar.add(cc.getTutSection());
        }

        if (Objects.isNull(cc.getPraSection())) {
            if (!cc.checkPra()) outputSoFar.add("PRA????");
        } else {
            outputSoFar.add(cc.getPraSection());
        }


        return outputSoFar;
    }

}
