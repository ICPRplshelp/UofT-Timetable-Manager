package p2tests;

import org.example.timetable.entities.WarningType;
import org.junit.Test;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcher;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WarningChecker2Tests {

    @Test(timeout = 10000)
    public void testCheckTimetableWarnings() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("MAT237Y1-Y");
        sc.addMeetingToPlannedCourse("MAT237Y1-Y", "LEC0101");

        wc.checkTimetableWarnings();

        assertTrue(wc.getLastMap().isEmpty());
    }


    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictChecker() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("MAT237Y1-Y");
        sc.addMeetingToPlannedCourse("MAT237Y1-Y", "LEC0101");
        sc.addCourse("ECO220Y1-Y");
        sc.addMeetingToPlannedCourse("ECO220Y1-Y", "LEC0101");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.CONFLICT);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

    @Test(timeout = 10000)
    public void testCheckTimetableWarningsDistanceChecker() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("MAT237Y1-Y");
        sc.addMeetingToPlannedCourse("MAT237Y1-Y", "LEC0101");

        sc.addCourse("PHL101Y1-Y");
        sc.addMeetingToPlannedCourse("PHL101Y1-Y", "LEC0101");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.DIST);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

    @Test(timeout = 10000)
    public void testCheckTimetableWarningsDistanceChecker2() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("MAT223H1-S");
        sc.addMeetingToPlannedCourse("MAT223H1-S", "LEC0401");

        sc.addCourse("MAT133Y1-Y");
        sc.addMeetingToPlannedCourse("MAT133Y1-Y", "LEC5102");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.DIST);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }


}
