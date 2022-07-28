package p1tests;

import org.example.requisitechecker.usecases.internal.CourseRegexSearcher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRegex {

    @Test(timeout = 50)
    public void testCourseRegex() {
        CourseRegexSearcher cRgx = new CourseRegexSearcher();
        String output = cRgx.lookForCourse("CSC110Y1");
        assertEquals("CSC110Y1", output);
    }

    @Test(timeout = 50)
    public void testBasic() {
        CourseRegexSearcher crgs = new CourseRegexSearcher();
        String input = "CSC110Y1";
        String lfsOutput = crgs.lookForCourse(input);
        assertEquals(input, lfsOutput);
    }
}
