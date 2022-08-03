package org.example.requisitechecker.courselocationtracker;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.requisitechecker.courselocationtracker.usecases.BuildingComparator;
import org.example.requisitechecker.courselocationtracker.usecases.BuildingStorageConstructor;
import org.phase2.studentrelated.usecases.CourseSearcher;

public class MainLocTest {
    public static void main(String[] args) {
        System.out.println("STARTING");
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcher csa = csgTemp.getCourseSearcher();
        var course = csa.getCourseOfferingByCode("20229", "CSC110Y1-F");
        var course2 = csa.getCourseOfferingByCode("20229", "CSC236H1-F");
        var sc1 = course.getMeetings().getLectures().get("LEC0101")
                .getScheduleEntries();
        var sc2 = course2.getMeetings().getLectures().get("LEC0201")
                .getScheduleEntries();

        String as1 = null;
        for (var r : sc1) {
            as1 = r.getAssignedRoom1();
            break;
        }

        String as2 = null;
        for (var r : sc2) {
            as2 = r.getAssignedRoom1();
            break;
        }

        var bsc = new BuildingStorageConstructor();
        var bsc2 = bsc.makeAllBuildings();
        BuildingComparator bc = new BuildingComparator(bsc2);
        var bcDist = bc.getDistance(as1, as2);
        System.out.println(bcDist);

    }
}
