package org.example.timetable.interfaceadapters;

import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorIndividual;
import org.example.timetable.usecases.TimetableManager;

import java.util.Collection;

public class TimetableController {



    private final Presenter presenter = new Presenter();
    private final TimetableCommunicatorBulk tcb;

    private TimetableManager timetableManager;

    public TimetableController(TimetableCommunicatorBulk tcb) {
        this.tcb = tcb;
    }


    public void presentTimeTable(){
        Collection<TimetableCommunicatorIndividual> indvC = tcb.getIndividualCommunicators();
        presenter.printTimetableInformation(indvC);
        //  presenter.printAllTimetableInformation(timetableManager.getTimetable());
    }



}
