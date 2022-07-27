package org.example.timetable.interfaceadapters;

import org.example.coursegetter.entities.Course;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorIndividual;
import org.example.timetable.usecases.WarningCommunicator;

import java.util.Collection;

public class TimetableController {


    private final Presenter presenter = new Presenter();
    private final TimetableCommunicatorBulk tcb;

    public TimetableController(TimetableCommunicatorBulk tcb) {
        this.tcb = tcb;
    }

    /**
     * Presents the current student timetable to the user.
     */
    public void presentTimeTable() {
        new WarningCommunicator(tcb.getTimetable());
        Collection<TimetableCommunicatorIndividual> indvC = tcb.getIndividualCommunicators();
        presenter.printTimetableInformation(indvC);
    }

    /**
     * Presents all previous courses taken to the user.
     */
    public void presentPreviousCourses() {
        Collection<Course> previousCourses = tcb.getTimetable().getPreviousCourses();
        presenter.printPrevCourseInformation(previousCourses);

    }


}
