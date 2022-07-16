package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

abstract public class TimetableWarning {
    protected WarningLevel warninglevel = WarningLevel.WARNING;

    public void setSeverity(WarningLevel severity) {
        this.warninglevel = severity;
    }

    public WarningLevel getWarningLevel(){
        return warninglevel;
    }
}
