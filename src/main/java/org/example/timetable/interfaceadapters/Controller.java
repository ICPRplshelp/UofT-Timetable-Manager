package org.example.timetable.interfaceadapters;

import org.example.timetable.entities.Timetable;
import org.example.timetable.usecases.TimetableManager;

public class Controller {

    private final Presenter presenter = new Presenter();

    private TimetableManager timetableManager;

    public void createTimeTable(){
        timetableManager = new TimetableManager(new Timetable());
    }

    public void presentTimeTable(){
        presenter.printAllTimetableInformation(timetableManager.getTimetable());
    }



}
