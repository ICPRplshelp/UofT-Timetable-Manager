import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.requisitechecker.entities.RequisiteList;
import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.requisitechecker.usecases.internal.ExclusionListBuilder;
import org.example.requisitechecker.usecases.internal.PrerequisiteListBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class TestExclusionListBuilder {

    @Test(timeout = 50)
    public void testExclusions() {
        String exl = "MAT235Y1, MAT257Y1, MATB41H3, MATB42H3, MATB43H3 & MAT368H5, MAT232H5 & MAT236H5";
        ExclusionListBuilder exb = new ExclusionListBuilder();
        RequisiteList extt = exb.buildRequisiteList(exl);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT235Y1"));
        assertTrue(extt.check(mc));
    }

    @Test(timeout = 50)
    public void testAST201(){
        String exl = "AST121H1, AST210H1, AST221H1, AST222H1, AST201H5, ASTA02H3, ASTB23H3. Also excluded are CIV100H1, CIV101H1, CIV102H1 and any 100- or higher-series CHM or PHY courses taken previously or concurrently (with the exception of PHY100H1, PHY100H5, PHY101H1, PHY201H1, PHY202H1, PHY205H1, PHY207H1, CHM101H1, CHM209H1; and AP, IB, CAPE, and GCE Transfer Credits)";
        ExclusionListBuilder exb = new ExclusionListBuilder();
        RequisiteList extt = exb.buildRequisiteList(exl);
        ArrayList<String> mc = new ArrayList<>(List.of("PHY100H1"));
        assertTrue(extt.check(mc));
    }

    @Test(timeout = 10000)
    public void testEX() {
        PrerequisiteListBuilder m = new PrerequisiteListBuilder();
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcherIndividual courseSearcherIndividual = csgTemp.getCourseSearcher();
        courseSearcherIndividual.getAllCoursesOfferingList("20229").forEach(crs -> {
            String pr = courseSearcherIndividual.getCourseOfferingByCode("20229", crs).getExclusion();
            RequisiteChecker rqc = new RequisiteChecker();
            rqc.checkExclusions(List.of("CSC110Y1"), pr);
            assertTrue(true);
        });
    }

}
