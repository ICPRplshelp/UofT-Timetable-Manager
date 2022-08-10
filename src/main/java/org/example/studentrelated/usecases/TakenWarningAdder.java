package org.example.studentrelated.usecases;

import org.example.timetable.entities.WarningType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TakenWarningAdder implements CourseWarningAdder {
    @Override
    public void addWarnings(Set<String> planned, Set<String> passed, Map<String, Set<WarningType>> warnList) {
        for(String crs : planned){
            checkPassedDuplicate(passed, warnList, crs);
            checkYDuplicate(planned, warnList, crs);
            checkSDuplicate(planned, warnList, crs);
        }
    }

    private void checkPassedDuplicate(Set<String> passed, Map<String, Set<WarningType>> warnList, String crs) {
        if(passed.contains(crs.substring(0, 8))){
            forceKeyToWarnList(warnList, crs);
            warnList.get(crs).add(WarningType.TAKEN);
        }
    }

    private void checkSDuplicate(Set<String> planned, Map<String, Set<WarningType>> warnList, String crs) {
        if(crs.endsWith("S")){
            if(planned.contains(crs.substring(0, 8) + "-F") ||
                    planned.contains(crs.substring(0, 8) + "-Y")){
                forceKeyToWarnList(warnList, crs);
                warnList.get(crs).add(WarningType.TAKEN);
            }
        }
    }

    private void checkYDuplicate(Set<String> planned, Map<String, Set<WarningType>> warnList, String crs) {
        if(crs.endsWith("Y")){
            if(planned.contains(crs.substring(0, 8) + "-F") ||
                    planned.contains(crs.substring(0, 8) + "-S")){
                forceKeyToWarnList(warnList, crs);
                warnList.get(crs).add(WarningType.TAKEN);
            }
        }
    }

    private void forceKeyToWarnList(Map<String, Set<WarningType>> warnList, String crs) {
        if(!warnList.containsKey(crs)){
            warnList.put(crs, new HashSet<>());
        }
    }
}
