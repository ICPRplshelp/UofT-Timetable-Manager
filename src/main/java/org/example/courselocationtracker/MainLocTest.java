package org.example.courselocationtracker;

import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.courselocationtracker.usecases.BuildingComparator;
import org.example.courselocationtracker.usecases.BuildingStorageConstructor;

public class MainLocTest {
    public static void main(String[] args) {
        System.out.println("STARTING");
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcherIndividual courseSearcherIndividual = csgTemp.getCourseSearcher();
        var course = courseSearcherIndividual.getCourseOfferingByCode("20229", "CSC110Y1-F");
        var course2 = courseSearcherIndividual.getCourseOfferingByCode("20229", "CSC236H1-F");
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
