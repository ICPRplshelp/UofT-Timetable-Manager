import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.internal.CourseSearcherIndividual;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBreadthRequirements {
    CourseSearcherGetter csgTemp = new CourseSearcherGetter();
    CourseSearcherIndividual courseSearcherIndividual = csgTemp.getCourseSearcher();

    @Test(timeout = 4500)
    public void testBrq() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        double[] ts = courseSearcherIndividual.getCourseOfferingByCode("ANT100Y1-Y")
                .brc.getBreadthContributions();

        assertEquals(Math.round(ts[0] * 2), 0);
        assertEquals(Math.round(ts[1] * 2), 0);
        assertEquals(Math.round(ts[2] * 2), 1);
        assertEquals(Math.round(ts[3] * 2), 1);
        assertEquals(Math.round(ts[4] * 2), 0);
    }

    @Test(timeout = 4500)
    public void testBrq2() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        double[] ts = courseSearcherIndividual.getCourseOfferingByCode("APM306Y1-Y")
                .brc.getBreadthContributions();

        assertEquals(Math.round(ts[0] * 2), 0);
        assertEquals(Math.round(ts[1] * 2), 0);
        assertEquals(Math.round(ts[2] * 2), 1);
        assertEquals(Math.round(ts[3] * 2), 0);
        assertEquals(Math.round(ts[4] * 2), 1);
    }

    @Test(timeout = 4500)
    public void testBrq3() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        double[] ts = courseSearcherIndividual.getCourseOfferingByCode("MAT135H1-F")
                .brc.getBreadthContributions();

        assertEquals(Math.round(ts[0] * 2), 0);
        assertEquals(Math.round(ts[1] * 2), 0);
        assertEquals(Math.round(ts[2] * 2), 0);
        assertEquals(Math.round(ts[3] * 2), 0);
        assertEquals(Math.round(ts[4] * 2), 1);
    }

    @Test(timeout = 4500)
    public void testBrq4() {

        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        double[] ts = courseSearcherIndividual.getCourseOfferingByCode("MAT137Y1-Y")
                .brc.getBreadthContributions();

        assertEquals(Math.round(ts[0] * 2), 0);
        assertEquals(Math.round(ts[1] * 2), 0);
        assertEquals(Math.round(ts[2] * 2), 0);
        assertEquals(Math.round(ts[3] * 2), 0);
        assertEquals(Math.round(ts[4] * 2), 2);
    }


}
