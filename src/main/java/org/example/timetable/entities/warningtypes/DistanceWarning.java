package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

public class DistanceWarning extends TimetableWarning{

    public DistanceWarning(){
        warninglevel = WarningLevel.WEAK_WARNING;
    }

    @Override
    public String toString(){
        return "Long travel dist. with previous crs";
    }

}
