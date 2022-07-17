package org.example.timetable.usecases;

import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.Timetable;

import java.time.LocalTime;
import java.util.*;

public class CourseConflictChecker {
    private final Timetable timetable;

    private HashMap<String, List<List<String>>> conflictMap;

    public CourseConflictChecker(Timetable timetable) {
        this.timetable = timetable;
    }

    /**
     * Given two course-lecture sections e.g. CSC110Y1-F-LEC0101
     * Checks if there is a conflict between the two lectures
     * Returns true if there is a conflict and false if there is no conflict
     */

    public void checkIfConflict(String c1, String c2) {
        List<String> courseList1 = List.of(c1.split("-"));   // [CSC110Y1, F, LEC0101]
        String courseCode = String.join("-", courseList1.get(0), courseList1.get(1));
        CourseChoice crs = CourseGetter(courseCode);

        List<String> courseList2 = List.of(c2.split("-"));
        String courseCode2 = String.join("-", courseList2.get(0), courseList2.get(1));
        CourseChoice crs2 = CourseGetter(courseCode2);


        assert crs != null;
        conflictMap.put(crs.getCourse().getCode(), checkIfConflictHelper(crs, crs2));
    }

    /**
     * Helper for checkIfConflict
     * returns a course choice based off the courseCode given
     */

    private CourseChoice CourseGetter(String courseCode) {
        List<CourseChoice> plannedCourses = new ArrayList<>(timetable.getPlannedCourses());
        for (int i = 0; i < plannedCourses.size(); i++) {
            if (plannedCourses.get(i).getCourse().getCode().equals(courseCode)) {
                return plannedCourses.get(i);
            }
        }
        return null;
    }

    /**
     * Helper for checkIfConflict
     * iterates through the ScheduleEntry of the Course lectures
     * returns true if there is a time conflict, false if there isn't
     */

    private List<List<String>> checkIfConflictHelper(CourseChoice crs, CourseChoice crs2) {
        List<List<String>> allList = new ArrayList<>();
        List<String> conflictListLec = new ArrayList<>();
        List<String> conflictListTut = new ArrayList<>();
        List<String> conflictListPra = new ArrayList<>();

        Meeting lecMeet = crs.getCourse().getMeetings().getLectures().get(crs.getLectureSection());
        Iterator<ScheduleEntry> c1 = lecMeet.getScheduleEntries().iterator();
        conflictListLec.add(crs.getLectureSection());
        conflictListLec.addAll(iteratorForIfConflictHelper(c1, crs2, conflictListLec));

        if (crs.getCourse().getMeetings().hasTutorials()) {
            Meeting tutMeet = crs.getCourse().getMeetings().getTutorials().get(crs.getTutSection());
            Iterator<ScheduleEntry> c2 = tutMeet.getScheduleEntries().iterator();
            conflictListTut.add(crs.getTutSection());
            conflictListTut.addAll(iteratorForIfConflictHelper(c2, crs2, conflictListTut));
        }

        if (crs.getCourse().getMeetings().hasPracticals()) {
            Meeting praMeet = crs.getCourse().getMeetings().getPracticals().get(crs.getPraSection());
            Iterator<ScheduleEntry> c3 = praMeet.getScheduleEntries().iterator();
            conflictListPra.add(crs.getPraSection());
            conflictListPra.addAll(iteratorForIfConflictHelper(c3, crs2, conflictListPra));
        }
        allList.add(conflictListLec);
        allList.add(conflictListTut);
        allList.add(conflictListPra);
        return allList;
    }
    private List<String> iteratorForIfConflictHelper(Iterator<ScheduleEntry> c1, CourseChoice crs2,
                                                     List<String> conflictList) {
        ScheduleEntry current;

        while(c1.hasNext()) {
            current = c1.next();

            Meeting lecMeet2 = crs2.getCourse().getMeetings().getLectures().get(crs2.getLectureSection());
            Iterator<ScheduleEntry> c2 = lecMeet2.getScheduleEntries().iterator();

            conflictList.add(iteratorForConflictHelperShorter(c2, current, "L", crs2));

            Meeting tutMeet2 = crs2.getCourse().getMeetings().getTutorials().get(crs2.getTutSection());
            Iterator<ScheduleEntry> c3 = tutMeet2.getScheduleEntries().iterator();

            conflictList.add(iteratorForConflictHelperShorter(c3, current, "T", crs2));

            Meeting praMeet2 = crs2.getCourse().getMeetings().getPracticals().get(crs2.getPraSection());
            Iterator<ScheduleEntry> c4 = praMeet2.getScheduleEntries().iterator();

            conflictList.add(iteratorForConflictHelperShorter(c4, current, "P", crs2));

        }
        return conflictList;
    }
    private String iteratorForConflictHelperShorter(Iterator<ScheduleEntry> c2, ScheduleEntry current,
                                                    String type, CourseChoice crs2) {
        ScheduleEntry current2;

        while(c2.hasNext()) {
            current2 = c2.next();
            LocalTime a = current.getStartTime();
            LocalTime b = current2.getStartTime();
            LocalTime c = current.getEndTime();
            LocalTime d = current2.getEndTime();

            boolean timeConflict = chkIfTimeConflict(a, b, c, d);

            if (current.getDay().equals(current2.getDay()) && timeConflict){
                if (Objects.equals(type, "L")) {
                    return crs2.getLectureSection();
                } else if (Objects.equals(type, "T")) {
                    return crs2.getTutSection();
                } else {return crs2.getPraSection();}
            }
        }
        return null;
    }

    /**
     * Helper for checkIfConflictHelper
     * Goes through the times provided
     * returns true if there is a conflict, false if there isn't
     */

    private boolean chkIfTimeConflict(LocalTime start, LocalTime start2, LocalTime end, LocalTime end2) {
        if ((start.compareTo(start2) > 0) && (start.compareTo(end2)) < 0) {
            return true;
        }
        else if ((end.compareTo(start2) > 0) && (end.compareTo(end2) < 0)) {
            return true;
        }
        else return (start.compareTo(start2) > 0) && (end.compareTo(end2) < 0);
    }

    public HashMap<String, List<List<String>>> getConflictMap() {return conflictMap;}

}
