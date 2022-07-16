package org.example.studentdata.entities;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.coursegetter.entities.Session;
import org.example.coursegetter.entities.SessionStorage;

import java.time.LocalTime;
import java.util.*;


public class Student {

    // all Strings MUST match the regex of a course offering.
    // e.g. CSC110Y1-F
    // the courses a student plans to take
    // based on the order it was added by the student
    // on the front-end side of this app

    // Exactly the same algorithm.
    private final List<CourseChoice> plannedFCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedSCourses  = new ArrayList<>();
    private final List<CourseChoice> plannedYCourses  = new ArrayList<>();
    // Use cases may only touch these lists, so let the
    // use cases do whatever we want to them

    public final SessionStorage previousCourses = new SessionStorage();


    public void addToPlannedFCourses(List<CourseChoice> plannedCourses){
        plannedFCourses.addAll(plannedCourses);
    }

    public void addToPlannedSCourses(List<CourseChoice> plannedCourses){
        plannedSCourses.addAll(plannedCourses);
    }

    public void addToPlannedYCourses(List<CourseChoice> plannedCourses){
        plannedYCourses.addAll(plannedCourses);
    }

    public List<CourseChoice> getPlannedFCourses() {
        return plannedFCourses;
    }

    public List<CourseChoice> getPlannedSCourses() {
        return plannedSCourses;
    }

    public List<CourseChoice> getPlannedYCourses() {
        return plannedYCourses;
    }

    private void flushCourses(){
        List<CourseChoice> plannedCourses = new ArrayList<>();
        plannedCourses.addAll(plannedFCourses);
        plannedCourses.addAll(plannedSCourses);
        plannedCourses.addAll(plannedYCourses);

        if (plannedCourses.size() == 0){
            return;
        }

        String session_name = plannedCourses.get(0).getCourse().getSession();
        Map<String, Course> courseMap = new HashMap<>();

        for (CourseChoice courseChoice: plannedCourses) {
            courseMap.put(courseChoice.getCourse().getCode(), courseChoice.getCourse());
        }

        Session session = new Session(courseMap, session_name);
        previousCourses.addSession(session_name, session);

        plannedFCourses.clear();
        plannedSCourses.clear();
        plannedYCourses.clear();
    }

    /**
     * Sort all the course lists.
     */
    public void sortAllCourseLists(){
        Collections.sort(plannedFCourses);
        Collections.sort(plannedSCourses);
        Collections.sort(plannedYCourses);
    }


    /**
     * Check if courses are back to back in a list of CourseChoice
     * returns an ArrayList of ArrayLists that contain two ScheduleEntry of courses that are back to back
     */

    public ArrayList<ArrayList<ScheduleEntry>> backToBackCourseChoices(List<CourseChoice> listCourses){
        ArrayList<ArrayList<ScheduleEntry>> backToBackCourses = new ArrayList<>();
        for (int i = 0; i < listCourses.size(); i++) {
            for (int n = 0; n < listCourses.size(); n ++) {
                backToBackCourses.add(chkIfCoursesBackToBack(listCourses.get(i), listCourses.get(n)));
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
        ScheduleEntry current = null;

        while(c1.hasNext() ) {
            current = c1.next();
            Iterator<ScheduleEntry> c2 = iterator2;
            ScheduleEntry current2 = null;
            while(c2.hasNext()) {
                current2 = c2.next();
                LocalTime a = current.getEndTime();
                LocalTime b = current2.getStartTime();
                LocalTime c = current.getStartTime();
                LocalTime d = current2.getEndTime();

//                System.out.println(c2.next());
                if (current.getDay().equals(current2.getDay()) && ((a == b) || (c == d))){
                    ArrayList<ScheduleEntry> newArray = new ArrayList<ScheduleEntry>();
                    newArray.add(current); newArray.add(current2);
                    return newArray;
                }
            }
        }
        return null;
    }

}
