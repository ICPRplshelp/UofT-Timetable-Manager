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

        String session = "20229";

        Set<ScheduleEntry> ts = courseSearcherIndividual.getCourseOfferingByCode(session,"MAT137Y1-Y")
                .getMeetings().getLectures().get("LEC0201").getScheduleEntries();
        System.out.println(ts);

        String tls = courseSearcherIndividual.getCourseOfferingByCode(session,"CSB196H1-S").getMeetings().getLectures().get("LEC0101").getEnrollmentControls().toString();
        System.out.println(tls);

        Set<String> codeList = courseSearcherIndividual.getAllCoursesOfferingList(session);

        // Set of courses that list sta237h1 as an exclusion
        // System.out.println(excSF);

        // Set of courses that require MAT237Y1 and not MAT235Y1
        Set<String> soFar2 = new HashSet<>();
        codeList.forEach(crs -> {
            var ex = courseSearcherIndividual.getCourseOfferingByCode(session, crs).getPrerequisite();
            var elb = new RequisiteChecker();
            var checkState1 = elb.check(List.of("MAT237Y1", "MAT223H1", "MAT224H1", "MAT246H1", "CSC236H1", "CSC263H1", "CSC209H1"), ex);
            var checkState2 = elb.check(List.of("MAT235Y1", "MAT223H1", "MAT224H1", "MAT246H1", "CSC236H1", "CSC263H1", "CSC209H1"), ex);
            if(checkState1 && !checkState2){
                soFar2.add(crs.substring(0, 8));
            }

        });
        System.out.println(soFar2);


    }
}
