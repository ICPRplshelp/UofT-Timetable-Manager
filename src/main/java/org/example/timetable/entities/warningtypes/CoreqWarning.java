package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

public class CoreqWarning extends TimetableWarning{

    public CoreqWarning()
    {
        warningType = WarningType.CRQ;
        warningLevel = WarningLevel.CRITICAL;
    }

    @Override
    public String toString(){
        return "Missing coreqs";
    }

}
