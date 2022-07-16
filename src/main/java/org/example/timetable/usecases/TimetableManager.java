package org.example.timetable.usecases;

import org.example.timetable.entities.Timetable;

public class TimetableManager {
    private final Timetable timetable;

    public TimetableManager(Timetable timetable){
        this.timetable = timetable;
    }

    public Timetable getTimetable() {
        return timetable;
    }
}
