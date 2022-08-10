package p2tests;

import org.example.timetable.entities.WarningType;
import org.junit.Test;
import org.example.studentrelated.controllers.StudentController;
import org.example.studentrelated.entities.Student2;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.example.studentrelated.usecases.StudentManager;
import org.example.studentrelated.usecases.WarningChecker2;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WarningChecker2Tests {


//    Checks to make sure no warnings are given with a single course
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsSingleCourse() {
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

//    Checks if there are no warnings are given with multiple courses that are not back to back and >650m or have conflicts

    @Test(timeout = 10000)
    public void testCheckTimetableWarningsMultipleCourses() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("MAT235Y1-Y");
        sc.addMeetingToPlannedCourse("MAT235Y1-Y", "LEC0101");
        sc.addCourse("CSC236H1-F");
        sc.addMeetingToPlannedCourse("CSC236H1-F", "TUT 0103");

        wc.checkTimetableWarnings();

        assertTrue(wc.getLastMap().isEmpty());
    }


//    Checks if there are conflicts between two year long courses
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictCheckerYearLongAndYearLong() {
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



//      Checked for a conflict warning between a fall and year long course where a schedule entry intersects with another entry but not the exact same schedule entry length
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictCheckerFallAndYearLong() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC110Y1-F");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "LEC0101");
        sc.addCourse("MAT137Y1-Y");
        sc.addMeetingToPlannedCourse("MAT137Y1-Y", "LEC0201");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.CONFLICT);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

    //      Checked for a conflict warning between a winter and year long course where a schedule entry intersects with another entry but not the exact same schedule entry length
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictCheckerWinterAndYearLong() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC111H1-S");
        sc.addMeetingToPlannedCourse("CSC111H1-S", "LEC0101");
        sc.addCourse("MAT137Y1-Y");
        sc.addMeetingToPlannedCourse("MAT137Y1-Y", "LEC0201");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.CONFLICT);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

    //      Checked for a conflict warning between a fall and fall course where a schedule entry intersects with another entry but not the exact same schedule entry length
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictCheckerFallAndFall() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC343H1-F");
        sc.addMeetingToPlannedCourse("CSC343H1-F", "LEC0101");
        sc.addCourse("CSC207-F");
        sc.addMeetingToPlannedCourse("CSC207H1-F", "LEC0101");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.CONFLICT);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

    //      Checked for a conflict warning between a winter and winter course where a schedule entry intersects with another entry but not the exact same schedule entry length
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictCheckerWinterAndWinter() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC148H1-S");
        sc.addMeetingToPlannedCourse("CSC148H1-S", "LEC0401");
        sc.addCourse("CSC343-S");
        sc.addMeetingToPlannedCourse("CSC343H1-S", "LEC0301");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.CONFLICT);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

    //      Checked if no conflict exists between two courses
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsConflictCheckerNoConflict() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC258H1-F");
        sc.addMeetingToPlannedCourse("CSC258H1-F", "LEC0101");
        sc.addCourse("MAT235Y1-Y");
        sc.addMeetingToPlannedCourse("MAT235Y1-Y", "LEC0101");

        Set<WarningType> warn = new HashSet<>();
        warn.add(WarningType.CONFLICT);

        for (int i = 0; i < wc.getLastMap().size(); i++) {
            assertEquals(wc.getLastMap().get(wc.getLastMap().keySet().toArray()[i]), warn);
        }
    }

//    Checks if no distance warning is given when courses are back to back and less than 650m but different buildings
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsDistanceCheckerShortDistance1() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC258H1-F");
        sc.addMeetingToPlannedCourse("CSC258H1-F", "LEC0101");

        sc.addCourse("CSC236H1-F");
        sc.addMeetingToPlannedCourse("CSC236H1-F", "LEC0101");

        wc.checkTimetableWarnings();

        assertTrue(wc.getLastMap().isEmpty());
    }

//    Checks if no distance warning is given when courses are back to back and in the same room
    @Test(timeout = 10000)
    public void testCheckTimetableWarningsDistanceCheckerSameBuilding() {
        Student2 stud = new Student2();
        UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
        UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
        StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
        StudentController sc = new StudentController(sm, null, null, null);
        WarningChecker2 wc = new WarningChecker2(usableCourseSearcher, sm.getPlannedCourses(), sm.getPassedCourses());

        sc.addCourse("CSC258H1-F");
        sc.addMeetingToPlannedCourse("CSC258H1-F", "LEC0101");

        sc.addCourse("MAT235Y1-Y");
        sc.addMeetingToPlannedCourse("MAT235Y1-Y", "LEC0101");

        wc.checkTimetableWarnings();

        assertTrue(wc.getLastMap().isEmpty());
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
