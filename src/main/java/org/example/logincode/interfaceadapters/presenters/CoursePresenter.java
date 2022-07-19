package org.example.logincode.interfaceadapters.presenters;

import java.util.Collection;

public class CoursePresenter extends Presenter {
    public String enterCourse() {
        prt.println("Enter course code: ");
        return scanner.nextLine();
    }

    public String enterSession() {
        prt.println("Enter course session: ");
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
