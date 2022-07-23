package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.interfaceadapters.presenters.AdminPresenter;
import org.example.logincode.interfaceadapters.presenters.CoursePresenter;
import org.example.logincode.interfaceadapters.presenters.StandardPresenter;
import org.example.logincode.interfaceadapters.presenters.TimetablePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputFactory {

    private AccountManager manager;
    private final StorageManager storageManager;
    private final CourseSearcherGetter csg;

    private final StandardPresenter standardPresenter = new StandardPresenter();
    private final AdminPresenter adminPresenter = new AdminPresenter();
    private final CoursePresenter coursePresenter = new CoursePresenter();
    private final TimetablePresenter timetablePresenter = new TimetablePresenter();

    public ControllerInputFactory(AccountManager manager,
                                  StorageManager storageManager,
                                  CourseSearcherGetter csg) {
        this.manager = manager;
        this.storageManager = storageManager;
        this.csg = csg;
    }

    public void setManager(AccountManager manager) {
        this.manager = manager;
    }

    /**
     * Obtain the controller input.
     *
     * @param typeOf the controller input to give me
     * @return the ControllerInput to return
     */
    public ControllerInput getControllerInput(LoggedInState typeOf) {
        switch (typeOf) {
            case STANDARD -> {
                return new ControllerInputStandard(manager, storageManager, standardPresenter);
            }
            case ADMIN -> {
                return new ControllerInputAdmin(manager, storageManager, adminPresenter);
            }
            case COURSE_SEARCHER -> {
                return new ControllerInputCourseSearch(manager, storageManager, coursePresenter, csg);
            }
            case TIMETABLE -> {
                return new ControllerInputTimetable(manager, storageManager, timetablePresenter, csg);
            }
            default -> {
                return null;
            }
        }

    }

}
