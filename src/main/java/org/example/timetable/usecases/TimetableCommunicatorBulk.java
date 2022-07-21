package org.example.timetable.usecases;

import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.Timetable;
import org.example.timetable.entities.WarningLevel;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;

import java.sql.Array;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TimetableCommunicatorBulk {

    private final Timetable timetable;

    public TimetableCommunicatorBulk(Timetable timetable) {
        this.timetable = timetable;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    /**
     * Returns the individual communicators for each timetable.
     * @return ^
     */
    public Collection<TimetableCommunicatorIndividual> getIndividualCommunicators(){
        List<TimetableCommunicatorIndividual> tcisSoFar = new ArrayList<>();
        Collection<CourseChoice> listCourseChoices = timetable.getPlannedCourses();
        for(CourseChoice cc : listCourseChoices){
            tcisSoFar.add(new TimetableCommunicatorIndividual(timetable, cc));
        }
        return tcisSoFar;
    }

}
