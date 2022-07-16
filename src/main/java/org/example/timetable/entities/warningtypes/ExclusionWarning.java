package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

public class ExclusionWarning extends TimetableWarning{

    public ExclusionWarning(){
        warninglevel = WarningLevel.CRITICAL;
    }

    @Override
    public String toString(){
        return "Exclusion found";
    }

}
