package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.interfaceadapters.presenters.TimetablePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.studentdata.usecases.StudentManager;
import org.example.studentdata.usecases.StudentManagerBuilder;
import org.example.timetable.interfaceadapters.TimetableController;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorBulkBuilder;

public class ControllerInputTimetable extends ControllerInput {
    private final CourseSearcherGetter csg;
    private final StudentManager sm;

    protected final TimetablePresenter presenter;

    private final TimetableController ttc;
    private final TimetableCommunicatorBulkBuilder timetableCommunicatorBulkBuilder = new TimetableCommunicatorBulkBuilder();

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param presenter             ^
     */
    public ControllerInputTimetable(AccountManager manager, StorageManager accountStorageManager, TimetablePresenter presenter,
                                    CourseSearcherGetter csg) {
        super(manager, accountStorageManager, presenter);
        this.presenter = presenter;
        ttc = new TimetableController(getTCB());
        StudentManagerBuilder smb = new StudentManagerBuilder();
        sm = smb.buildStudentManager(manager);
        this.csg = csg;

        this.curState = LoggedInState.TIMETABLE;

        commandsList = new String[]{
                "view",
                "viewprevcourses",
                "addcourse",
                "addmeetingtocourse",
                "addprevcourse",
                "delcourse",
                "delprevcourse",
                "back"};
    }

    @Override
    public boolean inputParser(String input) {

        switch (input) {
            case "view" -> {
                ttc.presentTimeTable();
                return true;
            }

            case "viewprevcourses" -> {
                ttc.presentPreviousCourses();
                return true;
            }

            case "addcourse" -> {
                return addCourse();
            }

            case "addmeetingtocourse" -> {
                return addMeetingToCourse();
            }

            case "addprevcourse" -> {
                return addPrevCourse();
            }

            case "delcourse" -> {
                return delCourse();
            }

            case "delprevcourse" -> {
                return delPrevCourse();
            }

            case "back" -> curState = LoggedInState.STANDARD;

            case "donothing" -> {
                return true;
            }

        }

        return false;
    }

    public TimetableCommunicatorBulk getTCB() {
        return timetableCommunicatorBulkBuilder.buildit(manager);
    }

    private boolean addCourse() {
        String rqc = presenter.addCourse();
        Course temp = csg.getCourseSearcher().getCourseOfferingByCode("20229", rqc);
        boolean addedCourseState = sm.addBlankPlannedCourse(temp);
        if (addedCourseState) {
            presenter.addCourseConfirmation();
        } else {
            presenter.addCourseError();
        }
        return true;
    }

    private boolean addMeetingToCourse() {
        String courseCode = presenter.addMeetingToCourse();
        if (sm.getPlannedCourseByString(courseCode) != null) {
            String sectionCode = presenter.addSectionToCourse();
            boolean setCourseState = sm.setCourseChoice(sm.getPlannedCourseByString(courseCode), sectionCode);
            if (setCourseState) {
                presenter.addMeetingConfirmation();
            } else presenter.addMeetingError();
        }
        return true;
    }

    private boolean addPrevCourse() {
        String courseCode = presenter.addPrevCourse();
        String session = presenter.addPrevSessionCourse(); // instructions for year + 9 / 5 etc.?
        Course temp = csg.getCourseSearcher().getCourseOfferingByCode(session, courseCode);
        boolean setCourseState = sm.addPreviousCourse(temp);
        if (setCourseState) {
            presenter.addPrevCourseConfirmation(session);
        } else presenter.addPrevCourseError();

        return setCourseState;
    }

    private boolean delCourse() {
        String courseCode = presenter.deleteCourse();

        boolean setCourseState = sm.removePlannedCourse(sm.getPlannedCourseByString(courseCode));
        if (setCourseState) {
            presenter.deleteCourseConfirmation();
        } else presenter.deleteCourseError();

        return setCourseState;
    }

    private boolean delPrevCourse() {
        String courseCode = presenter.deletePrevCourse();
        boolean setCourseState = sm.removePreviousCourse(sm.getPreviousCourseByString(courseCode));
        if (setCourseState) {
            presenter.deletePrevCourseConfirmation();
        } else presenter.deletePrevCourseError();

        return setCourseState;
    }

}
