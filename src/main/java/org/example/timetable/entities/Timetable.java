package org.example.timetable.entities;

import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Timetable  implements Serializable {


    private final Collection<CourseChoice> plannedCourses;

    public Timetable (Collection<CourseChoice> plannedCourses){
        this.plannedCourses = plannedCourses;
    }

    private final Map<CourseChoice, CourseWarning> warnings = new HashMap<>();


    public boolean addToTimetable(CourseChoice courseChoice) {
        return plannedCourses.add(courseChoice);
    }

    public boolean removeFromTimetable(CourseChoice courseChoice) {
        return plannedCourses.remove(courseChoice);
    }

    /**
     * @return all planned course choices so far.
     */
    public Collection<CourseChoice> getPlannedCourses(){
        return plannedCourses;
    }

    public Collection<CourseChoice> getPlannedCourses(String section){
        Stream<CourseChoice> temp = plannedCourses.stream().filter(crs -> crs.getCourse().getSession().equals(section));
        return temp.collect(Collectors.toList());
    }

    public void addWarning(CourseChoice courseChoice, TimetableWarning timetableWarning){
        if (!warnings.containsKey(courseChoice)){
            warnings.put(courseChoice, new CourseWarning());
        }

        warnings.get(courseChoice).addWarning(timetableWarning);

    }


    public CourseWarning getWarning(CourseChoice courseChoice) {
        return warnings.get(courseChoice);
    }
}

