package org.example.timetable.usecases;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.Timetable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
