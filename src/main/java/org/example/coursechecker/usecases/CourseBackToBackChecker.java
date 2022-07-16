package org.example.coursechecker.usecases;

import org.example.coursegetter.entities.ScheduleEntry;
import org.example.studentdata.entities.CourseChoice;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class CourseBackToBackChecker {
    /**
     * Check if courses are back to back in a list of CourseChoice
     * returns an ArrayList of ArrayLists that contain two ScheduleEntry of courses that are back to back
     */

    public ArrayList<ArrayList<ScheduleEntry>> backToBackCourseChoices(List<CourseChoice> listCourses){
        ArrayList<ArrayList<ScheduleEntry>> backToBackCourses = new ArrayList<>();
        for (int i = 0; i < listCourses.size(); i++) {
            for (CourseChoice listCourse : listCourses) {
                backToBackCourses.add(chkIfCoursesBackToBack(listCourses.get(i), listCourse));
            }
        }
        backToBackCourses.remove(null);
        return backToBackCourses;
    }


    /**
     * Check if two courses are back to back time-wise
     * For example if a course is 10:00-11:00 and another is 11:00-12:00 on the same day method will return True
     */

    public ArrayList<ScheduleEntry> chkIfCoursesBackToBack(CourseChoice Course1, CourseChoice Course2) {
        String lectureCode = Course1.getLectureSection();
        String lectureCode2 = Course2.getLectureSection();
        String tutorialCode = Course1.getTutSection();
        String tutorialCode2 = Course2.getTutSection();

        Iterator<ScheduleEntry> c1 = Course1.getCourse().getMeetings().getLectures().get(lectureCode).getScheduleEntries().iterator();
        Iterator<ScheduleEntry> c2 = Course2.getCourse().getMeetings().getLectures().get(lectureCode2).getScheduleEntries().iterator();
        Iterator<ScheduleEntry> c3 = Course1.getCourse().getMeetings().getTutorials().get(tutorialCode).getScheduleEntries().iterator();
        Iterator<ScheduleEntry> c4 = Course2.getCourse().getMeetings().getTutorials().get(tutorialCode2).getScheduleEntries().iterator();
        // checks if lectures from course 1 and course 2 are back to back
        if (backToBackHelper(c1, c2) != null) {return backToBackHelper(c1, c2);}
        // checks if lectures from course 1 and tutorials course 2 are back to back
        else if (backToBackHelper(c1, c4) != null) {return backToBackHelper(c1, c4);}
        // checks if tutorials from course 1 and lectures from course 2 are back to back
        else if (backToBackHelper(c3, c2) != null) {backToBackHelper(c3, c2);}
        // checks if tutorials from course 1 and tutorials from course 2 are back to back
        else {return backToBackHelper(c3, c4);}

        return null;
    }

    private ArrayList<ScheduleEntry> backToBackHelper(Iterator<ScheduleEntry> c1, Iterator<ScheduleEntry> iterator2) {
        ScheduleEntry current;

        while(c1.hasNext() ) {
            current = c1.next();
            ScheduleEntry current2;
            while(iterator2.hasNext()) {
                current2 = iterator2.next();
                LocalTime a = current.getEndTime();
                LocalTime b = current2.getStartTime();
                LocalTime c = current.getStartTime();
                LocalTime d = current2.getEndTime();

//                System.out.println(c2.next());
                if (current.getDay().equals(current2.getDay()) && ((a == b) || (c == d))){
                    ArrayList<ScheduleEntry> newArray = new ArrayList<>();
                    newArray.add(current); newArray.add(current2);
                    return newArray;
                }
            }
        }
        return null;
    }

}
