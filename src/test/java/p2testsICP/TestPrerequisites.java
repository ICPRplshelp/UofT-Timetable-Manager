package p2testsICP;

import org.example.timetable.entities.WarningType;
import org.example.studentrelated.controllers.StudentController;
import org.example.studentrelated.entities.Student2;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.example.studentrelated.usecases.StudentManager;
import org.example.studentrelated.usecases.WarningChecker2;
import org.junit.Assert;
import org.junit.Test;
import java.util.Map;
import java.util.Set;

public class TestPrerequisites {

    final Student2 stud = new Student2();
    final UsableCourseSearcherPrev usableCourseSearcherPrev = UsableCourseSearcherPrev.getInstance();
    final UsableCourseSearcher usableCourseSearcher = UsableCourseSearcher.getInstance();
    final StudentManager sm = new StudentManager(stud, usableCourseSearcher, usableCourseSearcherPrev);
    final StudentController sc = new StudentController(sm, null, null, null);
    final WarningChecker2 wc = new WarningChecker2(usableCourseSearcher,
            sm.getPlannedCourses(), sm.getPassedCourses());

    public void emptyLecTutPraWarnings(Map<String, Set<WarningType>> warns){
        for (Set<WarningType> warnSet : warns.values()) {
            warnSet.remove(WarningType.MISSING_LEC);
            warnSet.remove(WarningType.MISSING_TUT);
            warnSet.remove(WarningType.MISSING_PRA);
        }
    }

    /**
     * Trying out writing some tests
     */
    @Test
    public void testPrq1(){
        sc.addCourse("MAT337H1-F"); // prq issue
        sc.addCourse("MAT240H1-F"); // crq issue
        sc.addCourse("CSC110Y1-F"); // no issue
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("MAT337H1-F").contains(WarningType.PRQ));
        Assert.assertTrue(warnings.get("MAT240H1-F").contains(WarningType.CRQ));
        Assert.assertTrue(warnings.get("CSC110Y1-F").isEmpty());
    }

    /**
     * Is that enough to take the easier real analysis course?
     */
    @Test
    public void testPrq2(){
        sc.addHistoricalCourse("MAT257Y1");
        sc.addCourse("MAT337H1-F");
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("MAT337H1-F").isEmpty());
    }

    /**
     * Quirky chemistry requisites
     */
    @Test
    public void testPrq3(){
        // CHM151's corequisites are actually required, even
        // if the timetable says otherwise, according to
        // degree explorer
        sc.addCourse("CHM151Y1-Y");
        sc.addCourse("MAT137Y1-Y");
        sc.addCourse("PHY152H1-S");
        sc.addCourse("PHY131H1-S");
        sc.addCourse("PHY132H1-S");
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("CHM151Y1-Y").isEmpty());
        Assert.assertTrue(warnings.get("MAT137Y1-Y").isEmpty());
        Assert.assertTrue(warnings.get("PHY152H1-S").contains(WarningType.EXC));
    }

    /**
     * Stats minors be like
     */
    @Test
    public void testPrq4(){
        sc.addHistoricalCourse("STA130H1");
        sc.addHistoricalCourse("STA220H1");
        sc.addHistoricalCourse("STA221H1");
        sc.addHistoricalCourse("MAT137Y1");
        sc.addCourse("STA237H1-F");
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("STA237H1-F").isEmpty());

    }

    /**
     * I will proceed to count the number of people who tried taking
     * CSC263 without stats
     */
    @Test
    public void testPrq5(){
        sc.addHistoricalCourse("MAT137Y1");
        sc.addHistoricalCourse("CSC207H1");
        sc.addHistoricalCourse("CSC240H1");
        sc.addCourse("CSC263H1-F");
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        Assert.assertTrue(warnings.get("CSC263H1-F").contains(WarningType.PRQ));
    }

}
