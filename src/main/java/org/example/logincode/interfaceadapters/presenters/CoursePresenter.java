package org.example.logincode.interfaceadapters.presenters;

import java.util.Collection;

public class CoursePresenter extends Presenter {


    // Search courses by keyword (both current and past sessions)
    public String searchCoursesByKeyword() {
        prt.println("What courses would you like to search? Enter keyword the course code starts with (ie. CSC2): ");
        return scanner.nextLine();
    }

    public void searchCoursesByKeywordError() {
        prt.println("Couldn't find any courses matching the keyword.");
    }


    // Search course info AND search course LEC/TUT/PRA sections
    public String searchSingleCourse() {
        prt.println("Enter course offering of the course to search (ie CSC207H1-F): ");
        return scanner.nextLine();
    }

    public void searchSingleCourseError() {
        prt.println("Couldn't find course matching the course code.");
    }

    public String enterSession() {
        prt.println("Enter course session (ie 20229): ");
        return scanner.nextLine();
    }


    public void printListAndTitle(String title, Collection<String> listToPrint) {
        prt.println(title);
        for (String s : listToPrint) {
            prt.println(s);
        }
        prt.println("");    // spacer
    }

    public void printText(String text) {
        prt.println(text);
    }

}
