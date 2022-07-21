package org.example.timetable.entities;

import org.example.timetable.entities.warningtypes.TimetableWarning;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class CourseWarning  implements Serializable {

    private final Map<WarningLevel, Set<TimetableWarning>> warnings = new HashMap<>();

    public CourseWarning(){

    }

    /**
     * Flatten all warnings into a single set.
     * The set is sorted based on the warning concerned.
     * @return all warnings.
     */
    public Set<TimetableWarning> getAllWarnings() {
        return warnings.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(TreeSet::new));
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

            if (!(warnings.get(warningLevel) == null)) {
                List<TimetableWarning> aList = new ArrayList<>(warnings.get(warningLevel));
                return aList.get(0).getWarningLevel() + " WARNING " + aList.get(0).getWarningType() + " ERROR";
            }
        }
        return "No warnings found";
    }



}
