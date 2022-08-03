package org.phase2.studentrelated;

import org.phase2.htmltables.TableOrganizer;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.presenters.IScheduleEntry;
import org.phase2.studentrelated.presenters.StudentPresenter;
import org.phase2.studentrelated.usecases.CourseSearcher;
import org.phase2.studentrelated.usecases.CourseSearcherPrev;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MTesting {
    public static void main(String[] args) {
        Student2 stud = new Student2();
        CourseSearcherPrev courseSearcherPrev = new CourseSearcherPrev();
        CourseSearcher courseSearcher = new CourseSearcher();
        StudentManager sm = new StudentManager(stud, courseSearcher, courseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(courseSearcher, courseSearcherPrev,
                sm.getPlannedCourses(), sm.getPassedCourses());
        StudentPresenter studentPresenter = new StudentPresenter(wc, sm);
        sc.addCourse("MAT257Y1-Y");
        sc.addCourse("CSC110Y1-F");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "LEC0101");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "TUT0101");
        sc.addCourse("MAT137Y1-Y");
        sc.addMeetingToPlannedCourse("MAT137Y1-Y", "LEC5101");
        sc.addCourse("CHM151Y1-Y");
        sc.addHistoricalCourse("BIO120H1-F");
        sc.addHistoricalCourse("CHM135H1-S");

        String pcf = studentPresenter.getPlannedCourseInfo().toString();
        System.out.println(pcf);
        String pcf2 = studentPresenter.getPassedCourseInfo().toString();
        System.out.println(pcf2);
        TableOrganizer tableOrganizer = new TableOrganizer('F', wc);
        Map<Character, Set<IScheduleEntry>> pse = sm.getPlannedCourseSE();
        Set<IScheduleEntry> fTimetable = new HashSet<>();
        fTimetable.addAll(pse.get('F'));
        fTimetable.addAll(pse.get('Y'));
        String generatedTTString = tableOrganizer.generateHTMLTable(fTimetable);
        System.out.println(generatedTTString);
    }
}
