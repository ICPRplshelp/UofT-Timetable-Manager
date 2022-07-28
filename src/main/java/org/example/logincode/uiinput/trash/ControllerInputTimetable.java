package org.example.logincode.uiinput.trash;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.interfaceadapters.controllers.DONOTUSE2;
import org.example.logincode.interfaceadapters.presenters.TimetablePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.timetable.usecases.TimetableCommunicatorBulkBuilder;

public class ControllerInputTimetable extends ControllerInput {

    protected final TimetablePresenter presenter;

    private final TimetableCommunicatorBulkBuilder timetableCommunicatorBulkBuilder = new TimetableCommunicatorBulkBuilder();

    private final DONOTUSE2 controller;
    @Override
    public DONOTUSE2 getController() {
        return controller;
    }




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
        controller = new DONOTUSE2(manager, accountStorageManager, csg);
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
                controller.getTTC().presentTimeTable();
                return true;
            }

            case "viewprevcourses" -> {
                controller.getTTC().presentPreviousCourses();
                return true;
            }

            case "addcourse" -> {
                String rqc = presenter.addCourse();
                boolean success = controller.addCourse(rqc);
                if (success) {
                    presenter.addCourseConfirmation();
                } else {
                    presenter.addCourseError();
                }
                return success;
            }

            case "addmeetingtocourse" -> {
                String courseCode = presenter.addMeetingToCourse();
                String sectionCode = presenter.addSectionToCourse();
                boolean success = controller.addMeetingToCourse(courseCode, sectionCode);
                if (success) {
                    presenter.addMeetingConfirmation();
                } else presenter.addMeetingError();

                return success;
            }

            case "addprevcourse" -> {
                String courseCode = presenter.addPrevCourse();
                String session = presenter.addPrevSessionCourse(); // instructions for year + 9 / 5 etc.?
                boolean success = controller.addPrevCourse(session, courseCode);
                if (success) {
                    presenter.addPrevCourseConfirmation(session);
                } else presenter.addPrevCourseError();
                return success;
            }

            case "delcourse" -> {
                String courseCode = presenter.deleteCourse();
                boolean success = controller.delCourse(courseCode);
                if (success) {
                    presenter.deleteCourseConfirmation();
                } else presenter.deleteCourseError();
                return success;
            }

            case "delprevcourse" -> {
                String courseCode = presenter.deletePrevCourse();
                boolean success = controller.delPrevCourse(courseCode);
                if (success) {
                    presenter.deletePrevCourseConfirmation();
                } else presenter.deletePrevCourseError();
                return success;

            }

            case "back" -> controller.returnToStandardView();

            case "donothing" -> {
                return true;
            }

        }

        return false;
    }


}
