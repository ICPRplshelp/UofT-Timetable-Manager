package org.example.timetable.entities.warnings;

import java.util.Collection;

public class ConflictWarning extends TimetableWarning{
    private final Collection<String> courseConflict;

    public ConflictWarning(Collection<String> courseConflict){
        severity = "Warning";
        this.courseConflict = courseConflict;
    }

    @Override
    protected String warningInfo(){
        return "Conflicts with " + String.join(",", courseConflict);
    }

}
