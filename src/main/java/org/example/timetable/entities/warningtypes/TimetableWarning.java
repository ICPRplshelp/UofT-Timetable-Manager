package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

abstract public class TimetableWarning implements Comparable<TimetableWarning> {
    protected WarningType warningType;
    protected WarningLevel warningLevel = WarningLevel.WARNING;

    public void setSeverity(WarningLevel severity) {
        this.warningLevel = severity;
    }

    public WarningType getWarningType(){
        if (warningType == null) return WarningType.UNKNOWN;
        else return warningType;
    }

    public WarningLevel getWarningLevel(){
        return warningLevel;
    }

    @Override
    public int compareTo(TimetableWarning o) {
        return this.warningLevel.compareTo(o.warningLevel);
    }
}
