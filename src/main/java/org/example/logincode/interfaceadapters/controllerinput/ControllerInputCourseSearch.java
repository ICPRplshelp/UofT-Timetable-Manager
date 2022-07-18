package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseCommunicator;
import org.example.coursegetter.usecases.CourseSearcherByKeyword;
import org.example.coursegetter.usecases.CourseSearcherCommunicator;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;

import org.example.logincode.interfaceadapters.CoursePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.Collection;
import java.util.List;

public class ControllerInputCourseSearch extends ControllerInput {

    private final CourseSearcherIndividual courseSearcher;

    protected CoursePresenter presenter;

    /**
     * The constructor for this class.
     * All overrides MUST assign it a CRState.
     *
     * @param manager               always the same manager in the controller class
     * @param accountStorageManager ^
     * @param presenter             ^
     */
    public ControllerInputCourseSearch(AccountManager manager, StorageManager accountStorageManager, CoursePresenter presenter,
                                       CourseSearcherGetter csg) {
        super(manager, accountStorageManager, presenter);
        this.presenter = presenter;
        this.courseSearcher = csg.getCourseSearcher();
        this.curState = LoggedInState.COURSE_SEARCHER;
        commandsList = new String[]{"search", "sections"};
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

    private void promptSearchCourse(){

        String keyword = presenter.enterCourse();
        String session = presenter.enterSession();

        CourseSearcherByKeyword csk = new CourseSearcherByKeyword(courseSearcher);
        List<String> courseCodes = csk.getCoursesByKeyword(keyword, session);

        if (courseCodes.size() == 0) {
            presenter.genericFailedAction("invalid");
        } else {
            String title = String.format("Search Results for '%s': ", keyword);
            presenter.printListAndTitle(title, courseCodes);
        }
    }

    private void promptSearchSections(){

        String searchedCourse = presenter.enterCourse();
        String session = presenter.enterSession();

        CourseSearcherCommunicator csc = new CourseSearcherCommunicator(courseSearcher);
        CourseCommunicator courseCommunicator = csc.searchCourse(session, searchedCourse);

        if (courseCommunicator == null) {
            presenter.genericFailedAction("invalid");
        } else {
            // find alt way to print sections if available
            Collection<String> lectures = courseCommunicator.getLectures();
            Collection<String> tutorials = courseCommunicator.getTutorials();
            Collection<String> practicals = courseCommunicator.getPracticals();

            presenter.printListAndTitle("LEC", lectures);
            presenter.printListAndTitle("TUT", tutorials);
            presenter.printListAndTitle("PRA", practicals);
        }
    }

}
