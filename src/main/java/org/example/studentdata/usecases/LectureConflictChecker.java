package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

public class LectureConflictChecker {

    /**
     * Given two course-lecture sections e.g. CSC110Y1-F-LEC0101
     * Checks if there is a conflict between the two lectures
     * Returns true if there is a conflict and false if there is no conflict
     */

    public boolean checkIfConflict(String c1, String c2, String Session) {
        List<String> courseList1 = List.of(c1.split("-"));
        String courseCode = String.join("-", courseList1.get(0), courseList1.get(1));
        Course crs = CourseGetter(Session, courseCode);

        List<String> courseList2 = List.of(c2.split("-"));
        String courseCode2 = String.join("-", courseList2.get(0), courseList2.get(1));
        Course crs2 = CourseGetter(Session, courseCode2);

        String lectureCode = courseList1.get(2);
        String lectureCode2 = courseList2.get(2);

        return checkIfConflictHelper(lectureCode, lectureCode2, crs, crs2);

    }

    /**
     * Helper for checkIfConflict
     * returns a course based off the session and courseCode given
     */

    private Course CourseGetter(String Session, String courseCode) {
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        CourseSearcherIndividual courseSearcherIndividual = csgTemp.getCourseSearcher();

        return courseSearcherIndividual.getCourseOfferingByCode(Session,courseCode);
    }

    /**
     * Helper for checkIfConflict
     * iterates through the ScheduleEntry of the Course lectures
     * returns true if there is a time conflict, false if there isn't
     */

    private boolean checkIfConflictHelper(String lecCode, String lecCode2, Course crs, Course crs2) {
        Meeting meet = crs.getMeetings().getLectures().get(lecCode);
        Iterator<ScheduleEntry> c1 = meet.getScheduleEntries().iterator();

        ScheduleEntry current;

        while(c1.hasNext() ) {
            current = c1.next();
            Meeting meet2 = crs2.getMeetings().getLectures().get(lecCode2);
            Iterator<ScheduleEntry> c2 = meet2.getScheduleEntries().iterator();
            ScheduleEntry current2;
            while(c2.hasNext()) {
                current2 = c2.next();
                LocalTime a = current.getStartTime();
                LocalTime b = current2.getStartTime();
                LocalTime c = current.getEndTime();
                LocalTime d = current2.getEndTime();

                boolean timeConflict = chkIfTimeConflict(a, b, c, d);

                if (current.getDay().equals(current2.getDay()) && timeConflict){
                    return true;
                }
            }
        }
        return false;
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

}
