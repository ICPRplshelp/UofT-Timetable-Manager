package p2testsICP;

import org.example.timetable.entities.WarningType;
import org.junit.Assert;
import org.junit.Test;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcher;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.Map;
import java.util.Set;

public class TestFYFWarnings {
    Student2 stud = new Student2();
    UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
    UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
    StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
    StudentController sc = new StudentController(sm, null, null, null);
    WarningChecker2 wc = new WarningChecker2(usableCourseSearcher,
            sm.getPlannedCourses(), sm.getPassedCourses());

    public void emptyLecTutPraWarnings(Map<String, Set<WarningType>> warns){
        for (Set<WarningType> warnSet : warns.values()) {
            if (warnSet.contains(WarningType.MISSING_LEC)) {
                warnSet.remove(WarningType.MISSING_LEC);
            }
            if (warnSet.contains(WarningType.MISSING_TUT)) {
                warnSet.remove(WarningType.MISSING_TUT);
            }
            if (warnSet.contains(WarningType.MISSING_PRA)) {
                warnSet.remove(WarningType.MISSING_PRA);
            }
        }
    }

    /**
     * There goes your first year.
     */
    @Test
    public void testFYFWarning(){
        sc.addHistoricalCourse("MAT137Y1");
        sc.addHistoricalCourse("MAT235Y1");
        sc.addHistoricalCourse("CSC110Y1");
        sc.addHistoricalCourse("ENG140Y1");
        sc.addCourse("STA197H1-F");
        sc.addCourse("STA130H1-F");
        // normally, CSC110Y1-F would trigger a warning but the
        // enrolment controls haven't been that consistent
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("STA197H1-F").contains(WarningType.FYF));
        Assert.assertTrue(warnings.get("STA130H1-F").contains(WarningType.FYF));
    }

    /**
     * Remove one of them and it should work
     */
    @Test
    public void testFYFWarning2(){
        sc.addHistoricalCourse("MAT137Y1");
        sc.addHistoricalCourse("MAT235Y1");
        sc.addHistoricalCourse("CSC110Y1");
        sc.addHistoricalCourse("GGR124H1");
        sc.addCourse("STA197H1-F");
        sc.addCourse("STA130H1-F");
        // normally, CSC110Y1-F would trigger a warning but the
        // enrolment controls haven't been that consistent
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("STA197H1-F").isEmpty());
        Assert.assertTrue(warnings.get("STA130H1-F").isEmpty());
    }




}
