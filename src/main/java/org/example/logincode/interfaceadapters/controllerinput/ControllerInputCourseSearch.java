package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.CourseCommunicator;
import org.example.coursegetter.usecases.CourseSearcherCommunicator;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.logincode.interfaceadapters.Presenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.Collection;

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
        switch (input) {
            case "record" -> recordPastCourse();
            case "search" -> promptSearchCourse();
            case "add" -> addCourseToTimetable();
            default -> {
                return failedAction();
            }
        }
        return true;
    }

    private void recordPastCourse() {

//        String course = "CSC110Y1";
    }

    // I need all of its lecture sections (no need for timings)
    private void promptSearchCourse(){

        // placeholder
        String searchedCourse = presenter.enterCourse();
        String session = presenter.enterSession();

        // use CourseSearcherCommunicator to extract searched courses without
        // the need to violate clean architecture.
        CourseSearcherCommunicator csc = new CourseSearcherCommunicator(courseSearcher);
        CourseCommunicator courseCommunicator = csc.searchCourse(session, searchedCourse);

        if (courseCommunicator == null) {
            presenter.genericFailedAction("invalid");
        } else {
            Collection<String> lectures = courseCommunicator.getLectures();
            Collection<String> tutorials = courseCommunicator.getTutorials();
            Collection<String> practicals = courseCommunicator.getPracticals();

            presenter.printCourseSessionsByType("LEC", lectures);
            presenter.printCourseSessionsByType("TUT", tutorials);
            presenter.printCourseSessionsByType("PRA", practicals);
        }
    }

    private void addCourseToTimetable() {

    }

}
