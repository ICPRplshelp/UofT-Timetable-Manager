import org.example.requisitechecker.usecases.internal.CourseRegexSearcher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRegex {

    @Test(timeout = 50)
    public void testCourseRegex(){
        var cRgx = new CourseRegexSearcher();
        var output = cRgx.lookForCourse("CSC110Y1");
        assertEquals("CSC110Y1", output);
    }

    @Test(timeout = 50)
    public void testBasic(){
        var crgs = new CourseRegexSearcher();
        var input = "CSC110Y1";
        var lfsOutput = crgs.lookForCourse(input);
        assertEquals(input, lfsOutput);
    }
}
