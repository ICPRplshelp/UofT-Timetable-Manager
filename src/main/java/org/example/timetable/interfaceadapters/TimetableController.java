package org.example.timetable.interfaceadapters;

import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.requisitechecker.usecases.RequisiteChecker;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.Timetable;
import org.example.timetable.entities.WarningLevel;
import org.example.timetable.entities.warningtypes.TimetableWarning;
import org.example.timetable.entities.warningtypes.WarningType;
import org.example.timetable.usecases.TimetableCommunicatorBulk;
import org.example.timetable.usecases.TimetableCommunicatorIndividual;
import org.example.timetable.usecases.TimetableManager;
import org.example.timetable.usecases.WarningCommunicator;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TimetableController {



    private final Presenter presenter = new Presenter();
    private final TimetableCommunicatorBulk tcb;

    private TimetableManager timetableManager;

    public TimetableController(TimetableCommunicatorBulk tcb) {
        this.tcb = tcb;
    }


    public void presentTimeTable(){
        new WarningCommunicator(tcb.getTimetable());
        Collection<TimetableCommunicatorIndividual> indvC = tcb.getIndividualCommunicators();
        presenter.printTimetableInformation(indvC);
    }


}
