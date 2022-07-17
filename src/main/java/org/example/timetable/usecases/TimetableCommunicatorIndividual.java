package org.example.timetable.usecases;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.CourseWarning;
import org.example.timetable.entities.Timetable;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Communicates things relevant in Timetable to the controller
 * by only returning builtins and primitives.
 */
public class TimetableCommunicatorIndividual {
    private final Timetable timetable;
    private final CourseChoice cc;


    // umm actually please don't use singletons haha


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
        if(courseWarning == null) return new HashSet<>();
        Set<TimetableWarning> allWarns = courseWarning.getAllWarnings();
        return allWarns;
    }

    public List<String> getSectionsFromCourse() {
        List<String> outputSoFar = new ArrayList<>();
        if (!cc.checkLec()) {
            outputSoFar.add("LEC????");
        } else if (cc.getLectureSection() != null) {
            outputSoFar.add(cc.getLectureSection());
        }

        if (!cc.checkTut()) {
            outputSoFar.add("TUT????");
        } else if (cc.getTutSection() != null) {
            outputSoFar.add(cc.getTutSection());
        }

        if (!cc.checkPra()) {
            outputSoFar.add("PRA????");
        } else if (cc.getPraSection() != null) {
            outputSoFar.add(cc.getPraSection());
        }

        return outputSoFar;
    }

}
