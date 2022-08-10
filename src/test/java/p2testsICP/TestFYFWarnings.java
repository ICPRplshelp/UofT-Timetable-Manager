package p2testsICP;

import org.example.timetable.entities.WarningType;
import org.junit.Assert;
import org.junit.Test;
import org.example.studentrelated.controllers.StudentController;
import org.example.studentrelated.entities.Student;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.example.studentrelated.usecases.StudentManager;
import org.example.studentrelated.usecases.WarningChecker;

import java.util.Map;
import java.util.Set;

public class TestFYFWarnings {
    final Student stud = new Student();
    final UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
    final UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
    final StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
    final StudentController sc = new StudentController(sm, null, null, null);
    final WarningChecker wc = new WarningChecker(usableCourseSearcher,
            sm.getPlannedCourses(), sm.getPassedCourses());

    public void emptyLecTutPraWarnings(Map<String, Set<WarningType>> warns){
        for (Set<WarningType> warnSet : warns.values()) {
            warnSet.remove(WarningType.MISSING_LEC);
            warnSet.remove(WarningType.MISSING_TUT);
            warnSet.remove(WarningType.MISSING_PRA);
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
