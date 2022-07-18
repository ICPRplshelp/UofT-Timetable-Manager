package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseCommunicator;
import org.example.coursegetter.usecases.CourseSearcherCommunicator;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.logincode.interfaceadapters.CoursePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.Collection;

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

        // THIS METHOD IS CURRENTLY DEPRECATED

//        String keyword = presenter.enterCourse();
//        String session = presenter.enterSession();

        // use CourseSearcherCommunicator to extract searched courses without
        // the need to violate clean architecture.
//        CourseSearcherCommunicator csc = new CourseSearcherCommunicator(courseSearcher);
//        CourseCommunicator courseCommunicator = csc.searchCourse(session, keyword);
//
//        if (courseCommunicator == null) {
//            presenter.genericFailedAction("invalid");
//        } else {
////            Collection<String> lectures = courseCommunicator.getLectures();
////            Collection<String> tutorials = courseCommunicator.getTutorials();
////            Collection<String> practicals = courseCommunicator.getPracticals();
////
////            presenter.printCourseSessionsByType("LEC", lectures);
////            presenter.printCourseSessionsByType("TUT", tutorials);
////            presenter.printCourseSessionsByType("PRA", practicals);
//        }
    }

    private void promptSearchSections(){

        // placeholder
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

            presenter.printCourseSessionsByType("LEC", lectures);
            presenter.printCourseSessionsByType("TUT", tutorials);
            presenter.printCourseSessionsByType("PRA", practicals);
        }
    }

}
