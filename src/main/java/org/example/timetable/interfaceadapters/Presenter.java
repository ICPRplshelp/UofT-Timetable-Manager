package org.example.timetable.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.CourseWarning;
import org.example.timetable.entities.Section;
import org.example.timetable.entities.Timetable;

public class Presenter {

    PresenterPrinter prt = new PresenterPrinter();

    public void printAllTimetableInformation(Timetable timetable) {
        for (Section section: Section.values()) {
            printSectionInformation(timetable, section);
        }
    }

    private String sectionToString(Section section){
        return switch (section){
            case F -> "Fall";
            case S -> "Winter";
            case Y -> "Year";
        };
    }

    private void printSectionInformation(Timetable timetable, Section section){
        prt.println(sectionToString(section));
        for (CourseChoice courseChoice: timetable.getPlannedCourses(section)){
            printCourseInformation(courseChoice, timetable.getWarning(courseChoice));
        }
    }

    private void printCourseInformation(CourseChoice courseChoice, CourseWarning courseWarning){
        prt.println(courseChoice.toString() + " " + courseWarning.toString());
    }
}
