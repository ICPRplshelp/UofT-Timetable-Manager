package org.example.logincode.interfaceadapters;

import org.example.PresenterPrinter;

import java.util.Collection;
import java.util.Scanner;

public class CoursePresenter extends Presenter{
    public String enterCourse() {
        prt.println("Enter course code: ");
        return scanner.nextLine();
    }

    public String enterSession() {
        prt.println("Enter course session: ");
        return scanner.nextLine();
    }

    public void printCourseSessionsByType(String type, Collection<String> sessions) {
        prt.println(type);
        for (String s : sessions) {
            prt.println(s);
        }
        prt.println("");    // spacer
    }
}
