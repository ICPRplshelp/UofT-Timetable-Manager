package org.example.logincode.interfaceadapters.controllerinput;

import org.example.coursegetter.usecases.*;
import org.example.logincode.interfaceadapters.controllers.ControllerBase;
import org.example.logincode.interfaceadapters.controllers.ControllerCourseSearcher;
import org.example.logincode.interfaceadapters.presenters.CoursePresenter;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.Collection;
import java.util.List;

public class ControllerInputCourseSearch extends ControllerInput {

    private final ControllerCourseSearcher controller;
    @Override
    public ControllerBase getController() {
        return controller;
    }



    protected final CoursePresenter presenter;

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
        this.curState = LoggedInState.COURSE_SEARCHER;
        this.controller = new ControllerCourseSearcher(manager, accountStorageManager, csg);
        commandsList = new String[]{"search", "pastcourses", "courseinfo", "sections", "back"};
    }

    @Override
    public boolean inputParser(String input) {
        switch (input) {
            case "search" -> {
                String keyword = presenter.searchCoursesByKeyword();
                parseCourseListHelper(controller.searchCurrentCourses(keyword), keyword);
            }
            case "pastcourses" -> {
                String keyword = presenter.searchCoursesByKeyword();
                String session = presenter.enterSession();
                parseCourseListHelper(controller.searchPastCourses(keyword, session), keyword);
            }
            case "courseinfo" -> {
                String searchedCourse = presenter.searchSingleCourse();
                String session = presenter.enterSession();
                List<String> results = controller.searchCourseInfo(searchedCourse, session);
                if (results.isEmpty()) {
                    presenter.searchSingleCourseError();
                } else {
                    for (String entry : results) {
                        presenter.printText(entry);
                    }
                }
            }
            case "sections" -> {
                String searchedCourse = presenter.searchSingleCourse();
                String session = presenter.enterSession();
                List<Collection<String>> results = controller.searchSections(searchedCourse, session);
                if (results.isEmpty()) {
                    presenter.searchSingleCourseError();
                    presenter.printText("");
                } else {
                    presenter.printListAndTitle("LEC", results.get(0));
                    presenter.printListAndTitle("TUT", results.get(0));
                    presenter.printListAndTitle("PRA", results.get(0));
                }
            }
            case "back" -> controller.returnToStandardView();
            default -> {
                return failedAction();
            }
        }
        return true;
    }

    public void parseCourseListHelper(List<String> courseCodes, String keyword) {
        if (courseCodes.size() == 0) {
            presenter.searchCoursesByKeywordError();
            presenter.printText("");    // spacer
        } else {
            String title = String.format("Search Results for '%s': ", keyword);
            presenter.printListAndTitle(title, courseCodes);
        }
    }


}
