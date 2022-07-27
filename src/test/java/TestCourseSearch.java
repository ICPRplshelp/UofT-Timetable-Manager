import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.coursegetter.usecases.CourseSearcherByKeyword;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCourseSearch {

    final CourseSearcherGetter csgTemp = new CourseSearcherGetter();
    final CourseSearcherIndividual csi = csgTemp.getCourseSearcher();
    final CourseSearcherByKeyword csk = new CourseSearcherByKeyword(csi);


    @Test(timeout = 50)
    public void testSearchSingle() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        List<String> actual = csk.getCoursesByKeyword("CSC207", "20229");

        List<String> expected = new ArrayList<>(List.of("CSC207H1-F"));

        assert(expected.equals(actual));
    }

    @Test(timeout = 50)
    public void testSearchSingleTwoSections() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        List<String> actual = csk.getCoursesByKeyword("CSC311", "20229");

        List<String> expected = new ArrayList<>(Arrays.asList("CSC311H1-F", "CSC311H1-S"));

        assert(expected.equals(actual));
    }


    @Test(timeout = 50)
    public void testSearchMultiple() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        List<String> actual = csk.getCoursesByKeyword("CSC1", "20229");

        List<String> expected = new ArrayList<>(Arrays.asList("CSC108H1-F",
                "CSC108H1-S",
                "CSC110Y1-F",
                "CSC111H1-S",
                "CSC148H1-S",
                "CSC165H1-S",
                "CSC196H1-F",
                "CSC197H1-F",
                "CSC199H1-F"));

        assert(expected.equals(actual));
    }
}
