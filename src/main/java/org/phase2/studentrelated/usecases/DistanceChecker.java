package org.phase2.studentrelated.usecases;

import org.example.requisitechecker.courselocationtracker.usecases.BuildingComparator;
import org.example.requisitechecker.courselocationtracker.usecases.BuildingStorageConstructor;
import org.example.timetable.entities.WarningType;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DistanceChecker implements ScheduleWarningAdder  {
    final BuildingStorageConstructor buildingStorageConstructor = new BuildingStorageConstructor();
    final BuildingComparator buildingComparator = new BuildingComparator(buildingStorageConstructor.makeAllBuildings());

    public DistanceChecker() {
    }

    boolean checkBackToBack(IScheduleEntry se, Set<IScheduleEntry> allScheduleEntries) {
        char sesCode = se.getCourseCode().charAt(se.getCourseCode().length() - 1);
        for (IScheduleEntry se2 : allScheduleEntries) {
            char sesCode2 = se2.getCourseCode().charAt(se2.getCourseCode().length() - 1);
            if ((sesCode != 'Y' && sesCode2 != 'Y') && sesCode != sesCode2) {
                continue;
            }
            if (!se.getStartTime().equals(se2.getEndTime())) {
                continue;
            }
            boolean distanceState = checkDistance(se, se2);
            if (distanceState) {
                return true;
            }
        }
        return false;
    }

    /**
     * Are they too far?
     *
     * @param se  first one
     * @param se2 the second one (order does not matter)
     * @return true if they're over 650m apart.
     */
    boolean checkDistance(IScheduleEntry se, IScheduleEntry se2) {
        String fall1 = se.getAssignedRoom1();
        String winter1 = se.getAssignedRoom2();
        String fall2 = se2.getAssignedRoom1();
        String winter2 = se2.getAssignedRoom2();

        double distFall = buildingComparator.getDistance(fall1, fall2);
        double distWinter = buildingComparator.getDistance(winter1, winter2);

        double higherDist = Math.max(distFall, distWinter);
        // System.out.println(higherDist);
        return higherDist > 650;
    }

    @Override
    public void addWarnings(Set<IScheduleEntry> allScheduleEntries, Map<IScheduleEntry, Set<WarningType>> warningMap) {
        for(IScheduleEntry se : allScheduleEntries){
            if (checkBackToBack(se, allScheduleEntries)) {
                if (!warningMap.containsKey(se)) {
                    warningMap.put(se, new HashSet<>());
                }
                warningMap.get(se).add(WarningType.DIST);
            }
        }
    }
}
