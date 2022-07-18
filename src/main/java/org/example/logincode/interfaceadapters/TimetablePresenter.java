package org.example.logincode.interfaceadapters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TimetablePresenter extends Presenter{
    Map<String, String> userPrompt = Map.of(
        "view", "view the current timetable",
        "addcourse", "add a course",
        "addmeetingtocourse", "add a lecture time to a course",
        "addprevcourse", "add a previously taken course",
        "delcourse", "delete a current course",
        "delprevcourse", "delete a previously taken course",
        "back", "return to the standard user prompt"
    );
    public TimetablePresenter() {
    }

    public String printAndAskPrompt(String[] commandsList) {
        return dashboardView(inputPromptHelper(commandsList, userPrompt));
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
