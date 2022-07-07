import org.example.coursegetter.entities.ScheduleEntry;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.requisitechecker.usecases.RequisiteChecker;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("STARTING");
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcherIndividual courseSearcherIndividual = csgTemp.getCourseSearcher();
        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        Set<ScheduleEntry> ts = courseSearcherIndividual.getCourseOfferingByCode("MAT137Y1-Y")
                .getMeetings().getLectures().get("LEC0201").getScheduleEntries();
        System.out.println(ts);

        String tls = courseSearcherIndividual.getCourseOfferingByCode("CSB196H1-S").getMeetings().getLectures().get("LEC0101").enrollmentControls.toString();
        System.out.println(tls);

        var codeList = courseSearcherIndividual.getAllCoursesOfferingList();

        // Set of courses that list sta237h1 as an exclusion
        Set<String> excSF = new HashSet<>();
        var exToCheck = "STA237H1";
        codeList.forEach(crs -> {
           var ex = courseSearcherIndividual.getCourseOfferingByCode(crs).getExclusion();
           var elb = new RequisiteChecker(ex, true);
           var checkState = elb.check(List.of(exToCheck));
           if(checkState){
               excSF.add(crs.substring(0, 8));
           }
        });
        System.out.println(excSF);

        // Set of courses that require MAT237Y1 and not MAT235Y1
        Set<String> soFar2 = new HashSet<>();
        codeList.forEach(crs -> {
            var ex = courseSearcherIndividual.getCourseOfferingByCode(crs).getPrerequisite();
            var elb = new RequisiteChecker(ex, false);
            var checkState1 = elb.check(List.of("MAT237Y1", "MAT223H1", "MAT224H1", "MAT246H1", "CSC236H1", "CSC263H1", "CSC209H1"));
            var checkState2 = elb.check(List.of("MAT235Y1", "MAT223H1", "MAT224H1", "MAT246H1", "CSC236H1", "CSC263H1", "CSC209H1"));
            if(checkState1 && !checkState2){
                soFar2.add(crs.substring(0, 8));
            }

        });
        System.out.println(soFar2);
    }
}
