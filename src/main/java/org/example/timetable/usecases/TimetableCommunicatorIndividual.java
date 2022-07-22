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


    // umm, actually please don't use singletons haha


    public TimetableCommunicatorIndividual(Timetable timetable, CourseChoice cc) {
        this.timetable = timetable;
        this.cc = cc;
    }

    public String getCourseCode() {
        return cc.getCourse().getOfferingCode();
    }


    public List<WarningType> getWarningTypesList() {
        List<WarningType> soFar = new ArrayList<>();
        Set<TimetableWarning> allWarns = getAllWarnsFromCourseChoice();
        for (TimetableWarning ttw : allWarns) {
            soFar.add(ttw.getWarningType());
        }
        return soFar;
    }

    private Set<TimetableWarning> getAllWarnsFromCourseChoice() {
        CourseWarning courseWarning = timetable.getWarning(cc);
        if (courseWarning == null) return new HashSet<>();
        return courseWarning.getAllWarnings();
    }

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
