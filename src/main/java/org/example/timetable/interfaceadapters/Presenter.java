package org.example.timetable.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.CourseWarning;
import org.example.timetable.entities.Timetable;

public class Presenter {

    PresenterPrinter prt = new PresenterPrinter();

    public void printAllTimetableInformation(Timetable timetable) {
        printSectionInformation(timetable, "F");
        printSectionInformation(timetable, "S");
        printSectionInformation(timetable, "Y");
    }

    private String sectionFullName(String section){
        return switch (section){
            case "F" -> "Fall";
            case "S" -> "Winter";
            case "Y" -> "Year";
        };
    }

    private void printSectionInformation(Timetable timetable, String section){
        prt.println(sectionFullName(section));
        for (CourseChoice courseChoice: timetable.getPlannedCourses(section)){
            printCourseInformation(courseChoice, timetable.getWarning(courseChoice));
        }
    }

    private void printCourseInformation(CourseChoice courseChoice, CourseWarning courseWarning){
        prt.println(courseChoice.toString() + " " + courseWarning.toString());
    }
}
