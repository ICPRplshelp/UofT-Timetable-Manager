package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

public class YearOneWarning extends TimetableWarning{

    public YearOneWarning(){
        warninglevel = WarningLevel.CRITICAL;
    }

    @Override
    public String toString(){
        return "Year 1 only course";
    }

}
