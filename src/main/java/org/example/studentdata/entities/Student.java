package org.example.studentdata.entities;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Session;
import org.example.coursegetter.entities.SessionStorage;

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
}
