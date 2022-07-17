package org.example.timetable.interfaceadapters;

import org.example.timetable.entities.Timetable;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorIndividual;
import org.example.timetable.usecases.TimetableManager;

import java.util.Collection;

public class Controller {

    private final Presenter presenter = new Presenter();
    private final TimetableCommunicatorBulk tcb;

    private TimetableManager timetableManager;

    public Controller(TimetableCommunicatorBulk tcb) {
        this.tcb = tcb;
    }

    public void createTimeTable(){
        timetableManager = new TimetableManager(new Timetable());
    }

    public void presentTimeTable(){
        Collection<TimetableCommunicatorIndividual> indvC = tcb.getIndividualCommunicators();
        presenter.printTimetableInformation(indvC);
        //  presenter.printAllTimetableInformation(timetableManager.getTimetable());
    }



}
