import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;
import org.example.studentdata.usecases.StudentInferrer;
import org.example.timetable.entities.Timetable;
import org.example.timetable.usecases.WarningCommunicator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class TestWarningCommunicator {

    CourseSearcherGetter csg = new CourseSearcherGetter();


    @Test(timeout = 5600)
    public void testConflictWarning() {
        Course course = csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC110Y1-F");
        CourseChoice cc1 = new CourseChoice(course);
        cc1.setLectureSection("LEC0101");

        Course course2 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "MAT137Y1-Y");
        CourseChoice cc2 = new CourseChoice(course2);
        cc2.setLectureSection("LEC0101");

        Collection<CourseChoice> courseChoices = new ArrayList<>();

        courseChoices.add(cc1);
        courseChoices.add(cc2);

        Timetable timetable = new Timetable(courseChoices);
        new WarningCommunicator(timetable);

        assertEquals(timetable.getWarning(cc1).toString(), "[CONFLICT]");
        assertEquals(timetable.getWarning(cc2).toString(), "[CONFLICT]");
    }

    @Test(timeout = 5600)
    public void testExclusionWarning() {
        Course course = csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC108H1-F");
        CourseChoice cc1 = new CourseChoice(course);
        cc1.setLectureSection("LEC0101");

        Course course2 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC110Y1-F");
        CourseChoice cc2 = new CourseChoice(course2);
        cc2.setLectureSection("LEC0101");

        Collection<CourseChoice> courseChoices = new ArrayList<>();

        courseChoices.add(cc1);
        courseChoices.add(cc2);

        Timetable timetable = new Timetable(courseChoices);
        new WarningCommunicator(timetable);

        assertEquals(timetable.getWarning(cc1).toString(), "[EXC]");
        assertEquals(timetable.getWarning(cc2).toString(), "[EXC]");
    }

    @Test(timeout = 5600)
    public void testCoreqWarning() {
        Course course = csg.getCourseSearcher().getCourseOfferingByCode("20229", "STA130H1-F");
        CourseChoice cc1 = new CourseChoice(course);
        cc1.setLectureSection("LEC0101");

        Collection<CourseChoice> courseChoices = new ArrayList<>();

        courseChoices.add(cc1);

        Timetable timetable = new Timetable(courseChoices);
        new WarningCommunicator(timetable);

        assertEquals(timetable.getWarning(cc1).toString(), "[CRQ]");
    }

    @Test(timeout = 5600)
    public void testPrereqWarning() {
        Course course = csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC209H1-S");
        CourseChoice cc1 = new CourseChoice(course);
        cc1.setLectureSection("LEC0101");

        Collection<CourseChoice> courseChoices = new ArrayList<>();
        Collection<Course> c = new ArrayList<>();
        c.add(course);

        courseChoices.add(cc1);

        Timetable timetable = new Timetable(courseChoices);
        timetable.setPreviousCourses(c);
        new WarningCommunicator(timetable);

        assertEquals(timetable.getWarning(cc1).toString(), "[PRQ]");
    }

    @Test(timeout = 5600)
    public void testFYFWarning() {
        Course course = csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC110Y1-F");
        CourseChoice cc1 = new CourseChoice(course);
        cc1.setLectureSection("LEC0101");

        Course course2 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC209H1-S");

        Course course3 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "STA130H1-F");

        Course course4 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "MAT137Y1-Y");

        Course course5 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "HPS100H1-F");

        Course course6 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "HPS110H1-F");

        Course course7 = csg.getCourseSearcher().getCourseOfferingByCode("20229", "EAS100Y1-Y");

        Collection<CourseChoice> courseChoices = new ArrayList<>();
        Collection<Course> coursesPrev = new ArrayList<>();

        courseChoices.add(cc1);
        coursesPrev.add(course2);
        coursesPrev.add(course3);
        coursesPrev.add(course4);
        coursesPrev.add(course5);
        coursesPrev.add(course6);
        coursesPrev.add(course7);

        Timetable timetable = new Timetable(courseChoices);
        Student stud = new Student();
        stud.addToPreviousCourses(coursesPrev);
        double credits = new StudentInferrer(stud).getCreditCount();

        timetable.setPreviousCredits(credits);

        new WarningCommunicator(timetable);
        assertEquals(timetable.getWarning(cc1).toString(), "[FYF]");
    }

}
