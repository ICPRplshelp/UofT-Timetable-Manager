package org.example.timetable.entities;

import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.util.*;


public class CourseWarning {

    private final Map<WarningLevel, Set<TimetableWarning>> warnings = new HashMap<>();

    public CourseWarning(){

    }

    public void addWarning(TimetableWarning timetableWarning){
        if (!warnings.containsKey(timetableWarning.getWarningLevel())){
            warnings.put(timetableWarning.getWarningLevel(), new HashSet<>());
        }

        warnings.get(timetableWarning.getWarningLevel()).add(timetableWarning);
    }

    @Override
    public String toString(){
        ArrayList<String> result = new ArrayList<>();

        for (WarningLevel warningLevel: WarningLevel.values()) {
            Set<String> warningStrings = new HashSet<>();
            for (TimetableWarning timetableWarning: warnings.get(warningLevel)) {
                warningStrings.add(timetableWarning.toString());
            }

            result.add("[" + warningLevel.toString() + ": " + String.join(", ", warningStrings) + "]");
        }
        return String.join(", ", result);
    }

}
