import org.example.requisitechecker.usecases.internal.PrerequisiteListBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPrerequisiteListBuilder {

    @Test(timeout = 50)
    public void testMAT237_1() {
        var m = new PrerequisiteListBuilder();
        var courseStr = "[(MAT135H1, MAT136H1)/ (MAT135H5, MAT136H5)/ MAT134Y5/ MAT135Y5/ (MATA30H3/ MATA31H3, MATA36H3), MAT138H1/ MAT102H5/ MAT246H1]/ MAT137Y1/ MAT137Y5/ (MATA30H3/ MATA31H3, MATA37H3)/ MAT157Y1/ MAT157Y5, MAT223H1/ MATA22H3/ MATA23H3/ MAT240H1/ MAT240H5";
        var rql = m.buildRequisiteList(courseStr);
        // System.out.println(rql);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT135H1",
                "MAT136H1", "MAT133Y1", "MAT223H1"));
        var state = rql.check(mc);
        assertFalse(state);
    }

    @Test(timeout = 50)
    public void testMAT237_2() {
        var m = new PrerequisiteListBuilder();
        var courseStr = "[(MAT135H1, MAT136H1)/ (MAT135H5, MAT136H5)/ MAT134Y5/ MAT135Y5/ (MATA30H3/ MATA31H3, MATA36H3), MAT138H1/ MAT102H5/ MAT246H1]/ MAT137Y1/ MAT137Y5/ (MATA30H3/ MATA31H3, MATA37H3)/ MAT157Y1/ MAT157Y5, MAT223H1/ MATA22H3/ MATA23H3/ MAT240H1/ MAT240H5";
        var rql = m.buildRequisiteList(courseStr);
        // System.out.println(rql);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT135H1",
                "MAT136H1", "MAT138H1", "MAT223H1"));
        var state = rql.check(mc);
        assertTrue(state);
    }

    @Test(timeout = 50)
    public void testMAT237_3() {
        var m = new PrerequisiteListBuilder();
        var courseStr = "[(MAT135H1, MAT136H1)/ (MAT135H5, MAT136H5)/ MAT134Y5/ MAT135Y5/ (MATA30H3/ MATA31H3, MATA36H3), MAT138H1/ MAT102H5/ MAT246H1]/ MAT137Y1/ MAT137Y5/ (MATA30H3/ MATA31H3, MATA37H3)/ MAT157Y1/ MAT157Y5, MAT223H1/ MATA22H3/ MATA23H3/ MAT240H1/ MAT240H5";
        var rql = m.buildRequisiteList(courseStr);
        // System.out.println(rql);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT135H1",
                "MAT136H1", "MAT246H1", "MAT240H1"));
        var state = rql.check(mc);
        assertTrue(state);
    }

    @Test(timeout = 50)
    public void testCSC317_1() {
        var m = new PrerequisiteListBuilder();
        var courseStr = "MAT235Y1/ MAT237Y1/ MAT257Y1/ MAT291H1/ MAT292H1/ MAT294H1/ (MAT232H5, MAT368H5)/ (MAT233H5, MAT236H5)/ (MATB41H3, MATB42H3); MAT223H1/ MAT240H1/ MAT185H1/ MAT188H1; CSC209H1/ CSC209H5/ CSCB09H3/ proficiency in C or C++/ APS105H1/ ESC180H1/ CSC180H1";
        var rql = m.buildRequisiteList(courseStr);
        // System.out.println(rql);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT135H1",
                "MAT235Y1", "CSC209H1", "MAT223H1"));
        var state = rql.check(mc);
        assertTrue(state);
    }

    @Test(timeout = 50)
    public void testMAT437_1() {
        var m = new PrerequisiteListBuilder();
        var courseStr = "5.0 MAT credits, including MAT224H1/MAT247H1 and MAT237Y1/MAT257Y1";
        var rql = m.buildRequisiteList(courseStr);
        // System.out.println(rql);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT224H1", "MAT237Y1"));
        var state = rql.check(mc);
        assertTrue(state);
    }

    @Test(timeout = 50)
    public void testGGR493_1() {
        var m = new PrerequisiteListBuilder();
        var courseStr = "14.5 credits and an application";
        var rql = m.buildRequisiteList(courseStr);
        // System.out.println(rql);
        ArrayList<String> mc = new ArrayList<>(List.of());
        var state = rql.check(mc);
        assertTrue(state);
    }

}
