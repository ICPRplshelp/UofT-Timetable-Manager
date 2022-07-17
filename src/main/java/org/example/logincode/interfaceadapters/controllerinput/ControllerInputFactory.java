package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;
import org.example.logincode.interfaceadapters.Presenter;

public class ControllerInputFactory {

    private AccountManager manager;
    private final StorageManager storageManager;
    private final Presenter presenter;
    private final CourseSearcherGetter csg;

    public ControllerInputFactory(AccountManager manager,
                                  StorageManager storageManager,
                                  Presenter presenter,
                                  CourseSearcherGetter csg){
        this.manager = manager;
        this.storageManager = storageManager;
        this.presenter = presenter;
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
                return new ControllerInputStandard(manager, storageManager, presenter);
            }
            case ADMIN -> {
                return new ControllerInputAdmin(manager, storageManager, presenter);
            }
            case COURSE_SEARCHER -> {
                return new ControllerInputCourseSearch(manager, storageManager, presenter,
                        csg);
            }
            case TIMETABLE -> {
                return new ControllerInputTimetable(manager, storageManager,
                        presenter, csg);
            }
            default -> {
                return null;
            }
        }

    }

}
