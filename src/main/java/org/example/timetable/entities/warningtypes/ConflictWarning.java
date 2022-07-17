package org.example.timetable.entities.warningtypes;

import org.example.timetable.entities.WarningLevel;

import java.util.Collection;

public class ConflictWarning extends TimetableWarning{
    private final Collection<String> courseConflict;

    public ConflictWarning(Collection<String> courseConflict){
        warningType = WarningType.CONFLICT;
        warningLevel = WarningLevel.WARNING;
        this.courseConflict = courseConflict;
    }

    @Override
    public String toString(){
        return "Conflicts with " + String.join(", ", courseConflict);
    }

}
