import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.internal.CourseSearcherIndividual;

public class Main {
    public static void main(String[] args) {
        System.out.println("STARTING");
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcherIndividual courseSearcherIndividual = csgTemp.getCourseSearcher();
        // CREATE A BREAKPOINT AND DEBUG HERE TO TEST COURSES
        System.out.println("DONE");

        var ts = courseSearcherIndividual.getCourseOfferingByCode("MAT137Y1-Y")
                .meetings.getLectures().get("LEC0201").getScheduleEntries();
        System.out.println(ts);

        var tls = courseSearcherIndividual.getCourseOfferingByCode("CSB196H1-S").meetings.getLectures().get("LEC0101").enrollmentControls.toString();
        System.out.println(tls);
    }
}
