package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.*;
import org.example.logincode.interfaceadapters.LoginPresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.Collection;
import java.util.List;

public class ControllerInputCourseSearch extends ControllerInput {

    private final CourseSearcherIndividual courseSearcher;

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param loginPresenter             ^
     */
    public ControllerInputCourseSearch(AccountManager manager, StorageManager accountStorageManager, LoginPresenter loginPresenter,
                                       CourseSearcherGetter csg) {
        super(manager, accountStorageManager, loginPresenter);
        this.courseSearcher = csg.getCourseSearcher();
    }

    @Override
    public boolean inputParser(String input) {
        switch (input) {
            case "search" -> promptSearchCourse();
            case "sections" -> promptSearchSections();
            default -> {
                return failedAction();
            }
        }
        return true;
    }

    // I need all of its lecture sections (no need for timings)
    private void promptSearchCourse(){

        String keyword = loginPresenter.enterCourse();
        String session = loginPresenter.enterSession();

        CourseSearcherByKeyword csk = new CourseSearcherByKeyword(courseSearcher);
        List<String> courseCodes = csk.getCoursesByKeyword(keyword, session);

        if (courseCodes.size() == 0) {
            loginPresenter.genericFailedAction("invalid");
        } else {
            // placeholder for now. need to refactor printCourseSessionsByType to be more generalized
            loginPresenter.printCourseSessionsByType("placeholder", courseCodes);
        }
    }

    private void promptSearchSections(){

        // placeholder
        String searchedCourse = loginPresenter.enterCourse();
        String session = loginPresenter.enterSession();

        CourseSearcherCommunicator csc = new CourseSearcherCommunicator(courseSearcher);
        CourseCommunicator courseCommunicator = csc.searchCourse(session, searchedCourse);

        if (courseCommunicator == null) {
            loginPresenter.genericFailedAction("invalid");
        } else {
            // find alt way to print sections if available
            Collection<String> lectures = courseCommunicator.getLectures();
            Collection<String> tutorials = courseCommunicator.getTutorials();
            Collection<String> practicals = courseCommunicator.getPracticals();

            loginPresenter.printCourseSessionsByType("LEC", lectures);
            loginPresenter.printCourseSessionsByType("TUT", tutorials);
            loginPresenter.printCourseSessionsByType("PRA", practicals);
        }
    }

}
