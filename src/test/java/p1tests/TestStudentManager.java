package p1tests;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;
import org.example.studentdata.usecases.StudentManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestStudentManager {

    final StudentManager sm = new StudentManager(new Student());
    final CourseSearcherGetter csg = new CourseSearcherGetter();

    @Test(timeout = 5600)
    public void testAddAndRemovePlannedCourse() {
        sm.addBlankPlannedCourse(csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC110Y1-F"));
        assertEquals("CSC110Y1-F", sm.getPlannedCourseByString("CSC110Y1-F").getCourse().getOfferingCode());
        sm.removePlannedCourse(sm.getPlannedCourseByString("CSC110Y1-F"));
        assertNull(sm.getPlannedCourseByString("CSC110Y1-F"));

    }

    @Test(timeout = 4500)
    public void testSetCourseChoice1() {
        sm.addBlankPlannedCourse(csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC110Y1-F"));
        CourseChoice choice = sm.getPlannedCourseByString("CSC110Y1-F");
        sm.setCourseChoice(choice, "LEC0101");
        assertEquals("LEC0101", choice.getLectureSection());
    }

    @Test(timeout = 4500)
    public void testSetCourseChoice2() {
        sm.addBlankPlannedCourse(csg.getCourseSearcher().getCourseOfferingByCode("20229", "CSC110Y1-F"));
        CourseChoice choice = sm.getPlannedCourseByString("CSC110Y1-F");
        sm.setCourseChoice(choice, "TUT0101");
        assertEquals("TUT0101", choice.getTutSection());
    }

    @Test(timeout = 4500)
    public void AddAndRemovePreviousCourse() {
        Course course = csg.getCourseSearcher().getCourseOfferingByCode("20219", "MAT137Y1-Y");
        sm.addPreviousCourse(course);
        assertEquals("MAT137Y1", sm.getPreviousCourseByString("MAT137Y1").getCode());
        sm.removePreviousCourse(sm.getPreviousCourseByString("MAT137Y1"));
        assertNull(sm.getPreviousCourseByString("MAT137Y1"));
    }

}
