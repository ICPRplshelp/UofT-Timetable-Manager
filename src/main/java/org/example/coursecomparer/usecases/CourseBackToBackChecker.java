package org.example.coursecomparer.usecases;

import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Meetings;
import org.example.coursegetter.entities.ScheduleEntry;
import org.example.studentdata.entities.CourseChoice;

import java.time.LocalTime;
import java.util.*;


public class CourseBackToBackChecker {
    /**
     * Check if courses are back to back in a list of CourseChoice
     * returns an ArrayList of ArrayLists that contain two ScheduleEntry of courses that are back to back
     */

    public List<List<ScheduleEntry>> backToBackCourseChoices(List<CourseChoice> listCourses){
        List<List<ScheduleEntry>> backToBackCourses = new ArrayList<>();
        for (int i = 0; i < listCourses.size(); i++) {
            for (CourseChoice listCourse : listCourses) {
                backToBackCourses.add(checkIfCoursesBackToBack(listCourses.get(i), listCourse));
            }
        }
        backToBackCourses.remove(null);
        return backToBackCourses;
    }


    /**
     * Check if two courses are back to back time-wise
     * For example if a course is 10:00-11:00 and another is 11:00-12:00 on the same day method will return True
     */

    public List<ScheduleEntry> checkIfCoursesBackToBack(CourseChoice Course1, CourseChoice Course2) {
        String lectureCode = Course1.getLectureSection();
        String lectureCode2 = Course2.getLectureSection();
        String tutorialCode = Course1.getTutSection();
        String tutorialCode2 = Course2.getTutSection();
        String practicalCode = Course1.getPraSection();
        String practicalCode2 = Course2.getPraSection();

        Course c1Course = Course1.getCourse();
        Course c2Course = Course2.getCourse();
        Meetings c1Meetings = c1Course.getMeetings();
        Meetings c2Meetings = c2Course.getMeetings();
        Map<String, Meeting> c2Tutorials = c2Meetings.getTutorials();
        Map<String, Meeting> c1Tutorials = c1Meetings.getTutorials();
        Map<String, Meeting> c1Lectures = c1Meetings.getLectures();
        Map<String, Meeting> c2Lectures = c2Meetings.getLectures();
        Map<String, Meeting> c1Practicals = c1Meetings.getPracticals();
        Map<String, Meeting> c2Practicals = c2Meetings.getPracticals();
        Meeting c1Lecture = c1Lectures.get(lectureCode);
        Meeting c2Lecture = c2Lectures.get(lectureCode2);
        Meeting c1Tutorial = c1Tutorials.get(tutorialCode);
        Meeting c2Tutorial = c2Tutorials.get(tutorialCode2);
        Meeting c1Practical = c1Practicals.get(practicalCode);
        Meeting c2Practical = c2Practicals.get(practicalCode2);
        Set<ScheduleEntry> c1LectureSchedule = c1Lecture.getScheduleEntries();
        Set<ScheduleEntry> c2LectureSchedule = c2Lecture.getScheduleEntries();
        Set<ScheduleEntry> c1TutorialSchedule = c1Tutorial.getScheduleEntries();
        Set<ScheduleEntry> c2TutorialSchedule = c2Tutorial.getScheduleEntries();
        Set<ScheduleEntry> c1PracticalSchedule = c1Practical.getScheduleEntries();
        Set<ScheduleEntry> c2PracticalSchedule = c2Practical.getScheduleEntries();


        // checks if lectures from course 1 and course 2 are back to back
        if (backToBackHelper(c1LectureSchedule.iterator(), c2LectureSchedule.iterator()) != null)
        {return backToBackHelper(c1LectureSchedule.iterator(), c2LectureSchedule.iterator());}
        // checks if lectures from course 1 and tutorials course 2 are back to back
        else if (backToBackHelper(c1LectureSchedule.iterator(), c2TutorialSchedule.iterator()) != null)
        {return backToBackHelper(c1LectureSchedule.iterator(), c2TutorialSchedule.iterator());}
        // checks if tutorials from course 1 and lectures from course 2 are back to back
        else if (backToBackHelper(c1TutorialSchedule.iterator(), c2LectureSchedule.iterator()) != null)
        {backToBackHelper(c1TutorialSchedule.iterator(), c2LectureSchedule.iterator());}
        // checks if tutorials from course 1 and tutorials from course 2 are back to back
        else if (backToBackHelper(c1TutorialSchedule.iterator(), c2TutorialSchedule.iterator()) != null)
        {backToBackHelper(c1TutorialSchedule.iterator(), c2TutorialSchedule.iterator());}
        // checks if practicals from course 1 and practicals from course 2 are back to back
        else if (backToBackHelper(c1PracticalSchedule.iterator(), c2PracticalSchedule.iterator()) != null)
        {backToBackHelper(c1PracticalSchedule.iterator(), c2PracticalSchedule.iterator());}
        // checks if practicals from course 1 and lectures from course 1 are back to back
        else if (backToBackHelper(c1PracticalSchedule.iterator(), c1LectureSchedule.iterator()) != null)
        {backToBackHelper(c1PracticalSchedule.iterator(), c1LectureSchedule.iterator());}
        // checks if practicals from course 1 and tutorials from course 1 are back to back
        else if (backToBackHelper(c1PracticalSchedule.iterator(), c1TutorialSchedule.iterator()) != null)
        {backToBackHelper(c1PracticalSchedule.iterator(), c1TutorialSchedule.iterator());}
        // checks if practicals from course 1 and lectures from course 2 are back to back
        else if (backToBackHelper(c1PracticalSchedule.iterator(), c2LectureSchedule.iterator()) != null)
        {backToBackHelper(c1PracticalSchedule.iterator(), c2LectureSchedule.iterator());}
        // checks if practicals from course 1 and tutorials from course 2 are back to back
        else if (backToBackHelper(c1PracticalSchedule.iterator(), c2TutorialSchedule.iterator()) != null)
        {backToBackHelper(c1PracticalSchedule.iterator(), c2TutorialSchedule.iterator());}
        // checks if practicals from course 2 and lectures from course 1 are back to back
        else if (backToBackHelper(c2PracticalSchedule.iterator(), c1LectureSchedule.iterator()) != null)
        {backToBackHelper(c2PracticalSchedule.iterator(), c1LectureSchedule.iterator());}
        // checks if practicals from course 2 and tutorials from course 1 are back to back
        else if (backToBackHelper(c2PracticalSchedule.iterator(), c1TutorialSchedule.iterator()) != null)
        {backToBackHelper(c2PracticalSchedule.iterator(), c1TutorialSchedule.iterator());}
        // checks if practicals from course 2 and lectures from course 2 are back to back
        else if (backToBackHelper(c2PracticalSchedule.iterator(), c2LectureSchedule.iterator()) != null)
        {backToBackHelper(c2PracticalSchedule.iterator(), c2LectureSchedule.iterator());}
        // checks if practicals from course 2 and tutorials from course 2 are back to back
        else {return backToBackHelper(c2PracticalSchedule.iterator(), c2TutorialSchedule.iterator());}

        return null;
    }

    private List<ScheduleEntry> backToBackHelper(Iterator<ScheduleEntry> c1, Iterator<ScheduleEntry> iterator2) {
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
