package org.phase2.studentrelated;

import org.phase2.htmltables.TableOrganizer;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.presenters.IScheduleEntry;
import org.phase2.studentrelated.presenters.Presenter;
import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.CourseSearchAdapterPrev;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MTesting {
    public static void main(String[] args) {
        Student2 stud = new Student2();
        CourseSearchAdapterPrev courseSearchAdapterPrev = new CourseSearchAdapterPrev();
        CourseSearchAdapter courseSearchAdapter = new CourseSearchAdapter();
        StudentManager sm = new StudentManager(stud, courseSearchAdapter, courseSearchAdapterPrev);
        StudentController sc = new StudentController(sm);
        WarningChecker2 wc = new WarningChecker2(courseSearchAdapter, courseSearchAdapterPrev);
        Presenter presenter = new Presenter(wc, sm);
        sc.addCourse("CSC110Y1-F");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "LEC0101");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "TUT0101");
        sc.addHistoricalCourse("BIO120H1-F");
        sc.addHistoricalCourse("CHM135H1-S");
        String pcf = presenter.getPlannedCourseInfo().toString();
        System.out.println(pcf);
        String pcf2 = presenter.getPassedCourseInfo().toString();
        System.out.println(pcf2);
        TableOrganizer tableOrganizer = new TableOrganizer('F');
        Map<Character, Set<IScheduleEntry>> pse = sm.getPlannedCourseSE();
        Set<IScheduleEntry> fTimetable = new HashSet<>();
        fTimetable.addAll(pse.get('F'));
        fTimetable.addAll(pse.get('Y'));
        String generatedTTString = tableOrganizer.generateHTMLTable(fTimetable);
        System.out.println(generatedTTString);
    }
}
