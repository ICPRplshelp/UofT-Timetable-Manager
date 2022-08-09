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
import org.junit.Assert;
import org.junit.Test;
import java.util.Map;
import java.util.Set;

public class TestPrerequisites {

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
     * Trying out writing some tests
     */
    @Test
    public void testPrq1(){
        sc.addCourse("MAT337H1-F"); // prq issue
        sc.addCourse("MAT240H1-F"); // crq issue
        sc.addCourse("CSC110Y1-F"); // no issue
        Map<String, Set<WarningType>> warnings = wc.checkCourseWarnings();
        emptyLecTutPraWarnings(warnings);
        // test that MAT337H1-F has the PRQ warning
        Assert.assertTrue(warnings.get("MAT337H1-F").contains(WarningType.PRQ));
        // test that MAT240H1-F has the COR warning
        Assert.assertTrue(warnings.get("MAT240H1-F").contains(WarningType.CRQ));
        // test that CSC110Y1-F has no warnings
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
        // check that CHM151Y1-Y doesn't have warnings and that PHY152H1-S has a EXC warning
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
