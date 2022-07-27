package org.example.logincode.interfaceadapters.controllers;

import org.example.coursegetter.usecases.*;
import org.example.logincode.uiinput.trash.LoggedInState;
import org.example.logincode.usecases.AccountManager;
import org.example.logincode.usecases.StorageManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DONOTUSETHISCLASS extends ControllerBase {

    private final CourseSearcherIndividual courseSearcher;

    public DONOTUSETHISCLASS(AccountManager manager, StorageManager accountStorageManager,
                             CourseSearcherGetter csg) {
        super(manager, accountStorageManager);
        this.curState = LoggedInState.COURSE_SEARCHER;
        this.courseSearcher = csg.getCourseSearcher();
    }

    public List<String> searchCurrentCourses(String keyword) {
        CourseSearcherByKeyword csk = new CourseSearcherByKeyword(courseSearcher);
        return csk.getCoursesByKeyword(keyword, "20229");
    }

    public List<String> searchPastCourses(String keyword, String session) {
        CourseSearcherByKeyword csk = new CourseSearcherByKeyword(courseSearcher);
        return csk.getCoursesByKeyword(keyword, session);
    }

    public List<String> searchCourseInfo(String searchedCourse, String session) {

        CourseSearcherCommunicator csc = new CourseSearcherCommunicator(courseSearcher);
        CourseCommunicator cc = csc.searchCourse(session, searchedCourse);

        if (cc == null) {
            return new ArrayList<>();

        } else {
            return List.of(searchedCourse + ": " + cc.getCourseTitle(),
                    cc.getCourseDescription(),
                    cc.getPrerequisite(),
                    cc.getExclusion(),
                    cc.getBreadthCategories(),
                    cc.getDeliveryInstructions(),
                    ""); // spacer
        }
    }

    public List<Collection<String>> searchSections(String searchedCourse, String session) {

        CourseSearcherCommunicator csc = new CourseSearcherCommunicator(courseSearcher);
        CourseCommunicator courseCommunicator = csc.searchCourse(session, searchedCourse);

        if (courseCommunicator == null) {
            return new ArrayList<>();
        } else {
            return List.of(courseCommunicator.getLectures(), courseCommunicator.getTutorials(), courseCommunicator.getPracticals());
        }
    }



}
