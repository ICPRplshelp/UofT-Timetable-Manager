package org.example.logincode.interfaceadapters;

import org.example.PresenterPrinter;
import org.example.logincode.interfaceadapters.controllerinput.LoggedInState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimetablePresenter extends Presenter{

    Scanner scanner = new Scanner(System.in);
    PresenterPrinter prt = new PresenterPrinter();


    public TimetablePresenter() {
    }

    public String addCourse() {
        prt.println("What course would you like to add?");
        return scanner.nextLine();
    }

    public void addCourseConfirmation() {
        prt.println("Course added.");
    }

    public void addCourseError() {
        prt.println("Couldn't add the course - it either didn't exist or was a duplicate.");
    }

    public String addMeetingToCourse() {
        prt.println("What course would you add a section to?");
        return scanner.nextLine();
    }

    public String addSectionToCourse() {
        prt.println("What section would you like to add?");
        return scanner.nextLine();
    }

    public void addMeetingConfirmation() {
        prt.println("Meeting added.");
    }

    public void addMeetingError() {
        prt.println("That course doesn't have this meeting.");
    }

    public String addPrevCourse() {
        prt.println("What previous course would you like to add?");
        return scanner.nextLine();
    }

    public String addPrevSessionCourse() {
        prt.println("In which session did you take this course?");
        return scanner.nextLine();
    }


    public void addPrevCourseConfirmation(String session) {
        prt.println("Course added to session " + session + ".");
    }
    public void addPrevCourseError() {
        prt.println("That course is not available in that session.");
    }


    public String deleteCourse() {
        prt.println("What course would you like to delete?");
        return scanner.nextLine();
    }


    public void deleteCourseConfirmation() {
        prt.println("Course removed.");
    }
    public void deleteCourseError() {
        prt.println("You do not currently have that course.");
    }


    public String deletePrevCourse() {
        prt.println("What previous course would you like to remove?");
        return scanner.nextLine();
    }
    public void deletePrevCourseConfirmation() {
        prt.println("Course removed.");
    }
    public void deletePrevCourseError() {
        prt.println("You have not enrolled in that course before.");
    }

}
