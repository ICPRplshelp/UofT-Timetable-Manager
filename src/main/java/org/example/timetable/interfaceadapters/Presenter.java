package org.example.timetable.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.coursegetter.entities.baseclasses.Course;
import org.example.timetable.entities.warningtypes.WarningType;
import org.example.timetable.usecases.TimetableCommunicatorIndividual;

import java.util.*;

public class Presenter {

    final PresenterPrinter prt = new PresenterPrinter();
    final Map<WarningType, String> warningsMap;

    public Presenter() {
        EnumMap<WarningType, String> warningsMap = new EnumMap<>(WarningType.class);
        warningsMap.put(WarningType.CONFLICT, "Conflicts with another course");
        warningsMap.put(WarningType.CRQ, "Missing corequisite(s)");
        warningsMap.put(WarningType.PRQ, "Missing prerequisite(s)");
        warningsMap.put(WarningType.DIST, "Course has meeting location too far from b2b previous course");
        warningsMap.put(WarningType.EXC, "You have an exclusion");
        warningsMap.put(WarningType.FYF, "This course is Year 1 only");
        warningsMap.put(WarningType.UNKNOWN, "Unknown warning");
        this.warningsMap = warningsMap;
    }

    public void printTimetableInformation(Collection<TimetableCommunicatorIndividual> tcis) {
        prt.println("Timetable:");
        tcis.forEach(this::printCourseInformation);
    }

    private void printCourseInformation(TimetableCommunicatorIndividual tci) {
        String cc = tci.getCourseCode();
        List<String> sections = tci.getSectionsFromCourse();
        String sectionsString = String.join(" ", sections);
        StringBuilder sb = new StringBuilder();
        for (WarningType s : tci.getWarningTypesList()) {
            sb.append("[").append(warningsMap.get(s)).append("] ");
        }
        String warningsString = sb.toString();
        List<String> tempListString = List.of(cc, sectionsString, warningsString);
        String finalString = String.join(" // ", tempListString);
        prt.println(finalString);
    }

    public void printPrevCourseInformation(Collection<Course> previousCourses) {
        prt.println("Previous Courses:");
        List<Course> listCourse = new ArrayList<>(previousCourses);
        List<String> listCourseCode = new ArrayList<>();
        for (int i = 0; i < previousCourses.size(); i++) {
            listCourseCode.add(listCourse.get(i).getCode());
        }
        prt.println(String.valueOf(listCourseCode));

    }

}
