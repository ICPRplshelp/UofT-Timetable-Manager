package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

public class ExclusionWarning extends TimetableWarning{

    public ExclusionWarning(){

        this.warningType = WarningType.EXC;
        warningLevel = WarningLevel.CRITICAL;
    }

    @Override
    public String toString(){
        return "Exclusion found";
    }

}
