package p2tests;

import org.junit.Test;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.presenters.StudentPresenter;
import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.CourseSearchAdapterPrev;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class TestPrerequisites2 {
    Student2 stud = new Student2();
    CourseSearchAdapterPrev courseSearchAdapterPrev = new CourseSearchAdapterPrev();
    CourseSearchAdapter courseSearchAdapter = new CourseSearchAdapter();
    StudentManager sm = new StudentManager(stud, courseSearchAdapter, courseSearchAdapterPrev);
    WarningChecker2 wc = new WarningChecker2(courseSearchAdapter, courseSearchAdapterPrev);

    // it is permissible to call methods here
    StudentController sc = new StudentController(sm, null, null);
    StudentPresenter studentPresenter = new StudentPresenter(wc, sm);

    @Test
    public void checkCourseConflicts(){
        sc.addCourse("CSC111H1-S");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "LEC0101");
        sc.addMeetingToPlannedCourse("CSC110Y1-F", "TUT0101");
        // sc.addCourse("MAT137Y1-Y");
        // sc.addMeetingToPlannedCourse("MAT137Y1-Y", "LEC0401");
        Collection<String> pcrs = studentPresenter.getPlannedCourseInfo();
        System.out.println(pcrs);
        // check if substring "CONFLICT" appears in pcrs
        boolean conflictState = false;
        for(String s: pcrs){
            if(s.contains("PRQ")){
                conflictState = true;
                break;
            }
        }
        assertTrue(conflictState);
    }
}
