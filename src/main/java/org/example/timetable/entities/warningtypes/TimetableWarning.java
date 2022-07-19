package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

import java.io.Serializable;

abstract public class TimetableWarning implements Serializable,  Comparable<TimetableWarning> {
    protected WarningType warningType;
    protected WarningLevel warningLevel = WarningLevel.WARNING;

    public void setSeverity(WarningLevel severity) {
        this.warningLevel = severity;
    }

    public WarningType getWarningType(){
        if (warningType == null) return WarningType.UNKNOWN;
        else return warningType;
    }

    public void setWarningType(WarningType warning){
        warningType = warning;
    }

    public WarningLevel getWarningLevel(){
        return warningLevel;
    }

    @Override
    public int compareTo(TimetableWarning o) {
        return this.warningLevel.compareTo(o.warningLevel);
    }
}
