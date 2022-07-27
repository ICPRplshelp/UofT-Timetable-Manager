package org.example.logincode.interfaceadapters.controllers;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;
import org.example.logincode.interfaceadapters.presenters.TimetablePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.studentdata.usecases.StudentManager;
import org.example.studentdata.usecases.StudentManagerBuilder;
import org.example.timetable.interfaceadapters.TimetableController;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorBulkBuilder;

public class ControllerTimetable extends ControllerBase {
    private final CourseSearcherGetter csg;
    private final StudentManager sm;

    public TimetableController getTTC() {
        return ttc;
    }

    private final TimetableController ttc;
    private final TimetableCommunicatorBulkBuilder timetableCommunicatorBulkBuilder = new TimetableCommunicatorBulkBuilder();

    public ControllerTimetable(AccountManager manager, StorageManager accountStorageManager,
                               CourseSearcherGetter csg) {
        super(manager, accountStorageManager);
        ttc = new TimetableController(getTCB());
        StudentManagerBuilder smb = new StudentManagerBuilder();
        sm = smb.buildStudentManager(manager);
        this.csg = csg;

        this.curState = LoggedInState.TIMETABLE;
    }

    public TimetableCommunicatorBulk getTCB() {
        return timetableCommunicatorBulkBuilder.buildit(manager);
    }

    public boolean addCourse(String rqc) {

        Course temp = csg.getCourseSearcher().getCourseOfferingByCode("20229", rqc);
        return sm.addBlankPlannedCourse(temp);
    }

    public boolean addMeetingToCourse(String courseCode, String sectionCode) {

        if (sm.getPlannedCourseByString(courseCode) != null) {

            return sm.setCourseChoice(sm.getPlannedCourseByString(courseCode), sectionCode);

        }
        return false;
    }

    public boolean addPrevCourse(String session, String courseCode) {
        Course temp = csg.getCourseSearcher().getCourseOfferingByCode(session, courseCode);
        return sm.addPreviousCourse(temp);
    }

    public boolean delCourse(String courseCode) {
        return sm.removePlannedCourse(sm.getPlannedCourseByString(courseCode));
    }

    public  boolean delPrevCourse(String courseCode) {
        return sm.removePreviousCourse(sm.getPreviousCourseByString(courseCode));
    }
}
