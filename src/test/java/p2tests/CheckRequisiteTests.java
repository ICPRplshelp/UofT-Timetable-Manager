package p2tests;

import org.example.coursegetter.entities.baseclasses.Course;
import org.example.timetable.entities.WarningType;
import org.junit.Test;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.usecases.CheckRequisite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CheckRequisiteTests {

    @Test(timeout = 5000)
    public void testPrereqChecker() {
        UsableCourseSearcher searcher = UsableCourseSearcher.getInstance();

        Course crs = searcher.getCourse("MAT237Y1-Y");
        CheckRequisite cr = new CheckRequisite(crs.getCode());
        Set<String> allCourses = new HashSet<>();
        Map<String, Set<WarningType>> warnList = new HashMap<>();

        cr.prereqChecker(allCourses, crs.getPrerequisite(), warnList);
        Set<WarningType> cWarning = new HashSet<>();
        cWarning.add(WarningType.PRQ);

        Map<String, Set<WarningType>> correctWarnList = new HashMap<>(); correctWarnList.put("MAT237Y1", cWarning);
        assertEquals(warnList, correctWarnList);
    }

    @Test(timeout = 5000)
    public void testCoreqChecker() {
        UsableCourseSearcher searcher = UsableCourseSearcher.getInstance();

        Course crs = searcher.getCourse("PHY151H1-F");
        CheckRequisite cr = new CheckRequisite(crs.getCode());
        Set<String> allCourses = new HashSet<>();
        allCourses.add("MAT137Y1");
        Map<String, Set<WarningType>> warnList = new HashMap<>();

        cr.coreqChecker(allCourses, crs.getCorequisite(), warnList);

        Map<String, Set<WarningType>> correctWarnList = new HashMap<>();
        assertEquals(warnList, correctWarnList);
    }

    @Test(timeout = 5000)
    public void testCoreqChecker2(){
        UsableCourseSearcher searcher = UsableCourseSearcher.getInstance();

        Course crs = searcher.getCourse("PHY151H1-F");
        CheckRequisite cr = new CheckRequisite(crs.getCode());
        Set<String> allCourses = new HashSet<>();
        Map<String, Set<WarningType>> warnList = new HashMap<>();

        cr.coreqChecker(allCourses, crs.getCorequisite(), warnList);
        Set<WarningType> cWarning = new HashSet<>();
        cWarning.add(WarningType.CRQ);

        Map<String, Set<WarningType>> correctWarnList = new HashMap<>();correctWarnList.put("PHY151H1", cWarning);
        assertEquals(warnList, correctWarnList);
    }

    @Test(timeout = 5000)
    public void testExclusionChecker() {
        UsableCourseSearcher searcher = UsableCourseSearcher.getInstance();

        Course crs = searcher.getCourse("MAT301H1-F");
        CheckRequisite cr = new CheckRequisite(crs.getCode());
        Set<String> allCourses = new HashSet<>();
        allCourses.add("MAT347Y1");
        Map<String, Set<WarningType>> warnList = new HashMap<>();

        cr.exclusionChecker(allCourses, crs.getExclusion(), warnList);
        Set<WarningType> cWarning = new HashSet<>();
        cWarning.add(WarningType.EXC);

        Map<String, Set<WarningType>> correctWarnList = new HashMap<>();correctWarnList.put("MAT301H1", cWarning);
        assertEquals(warnList, correctWarnList);
    }

    @Test(timeout = 5000)
    public void testExclusionChecker2() {
        UsableCourseSearcher searcher = UsableCourseSearcher.getInstance();

        Course crs = searcher.getCourse("MAT301H1-F");
        CheckRequisite cr = new CheckRequisite(crs.getCode());
        Set<String> allCourses = new HashSet<>();
        Map<String, Set<WarningType>> warnList = new HashMap<>();

        cr.exclusionChecker(allCourses, crs.getExclusion(), warnList);

        Map<String, Set<WarningType>> correctWarnList = new HashMap<>();
        assertEquals(warnList, correctWarnList);
    }
}
