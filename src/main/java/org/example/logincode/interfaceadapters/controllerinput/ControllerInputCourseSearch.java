package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseCommunicator;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.logincode.interfaceadapters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

public class ControllerInputCourseSearch extends ControllerInput {

    private final CourseSearcherIndividual courseSearcher;

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param presenter             ^
     */
    public ControllerInputCourseSearch(AccountManager manager, StorageManager accountStorageManager, Presenter presenter,
                                       CourseSearcherGetter csg) {
        super(manager, accountStorageManager, presenter);
        this.courseSearcher = csg.getCourseSearcher();
    }

    @Override
    public boolean inputParser(String input) {
        return false;
    }

    // I need all of its lecture sections (no need for timings)
    private void promptSearchCourse(){
        String searchedCourse = "CSC110Y1-F";
        String session = "20229";
        // use CourseSearcherCommunicator to extract searched courses without
        // the need to violate clean architecture.



    }

}
