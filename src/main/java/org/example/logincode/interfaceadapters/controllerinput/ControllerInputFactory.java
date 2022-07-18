package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.interfaceadapters.LoginPresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputFactory {

    private AccountManager manager;
    private final StorageManager storageManager;
    private final LoginPresenter loginPresenter;
    private final CourseSearcherGetter csg;

    public ControllerInputFactory(AccountManager manager,
                                  StorageManager storageManager,
                                  LoginPresenter loginPresenter,
                                  CourseSearcherGetter csg){
        this.manager = manager;
        this.storageManager = storageManager;
        this.loginPresenter = loginPresenter;
        this.csg = csg;
    }

    public void setManager(AccountManager manager) {
        this.manager = manager;
    }

    /**
     * Obtain the controller input.
     * @param typeOf the controller input to give me
     *
     * @return the ControllerInput to return
     */
    public ControllerInput getControllerInput(LoggedInState typeOf
                                              ){
        switch (typeOf) {
            case STANDARD -> {
                return new ControllerInputStandard(manager, storageManager, loginPresenter);
            }
            case ADMIN -> {
                return new ControllerInputAdmin(manager, storageManager, loginPresenter);
            }
            case COURSE_SEARCHER -> {
                return new ControllerInputCourseSearch(manager, storageManager, loginPresenter,
                        csg);
            }
            case TIMETABLE -> {
                return new ControllerInputTimetable(manager, storageManager,
                        loginPresenter, csg);
            }
            default -> {
                return null;
            }
        }

    }

}
