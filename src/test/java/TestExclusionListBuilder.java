import org.example.requisitechecker.entities.TemplateList;
import org.example.requisitechecker.usecases.internal.ExclusionListBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class TestExclusionListBuilder {

    @Test(timeout = 50)
    public void testExclusions() {
        String exl = "MAT235Y1, MAT257Y1, MATB41H3, MATB42H3, MATB43H3 & MAT368H5, MAT232H5 & MAT236H5";
        ExclusionListBuilder exb = new ExclusionListBuilder();
        TemplateList extt = exb.buildRequisiteList(exl);
        ArrayList<String> mc = new ArrayList<>(List.of("MAT235Y1"));
        assertTrue(extt.check(mc));
    }
}
