package org.example.coursecomparer.usecases;

import org.example.coursecomparer.entities.CourseTimeslots;
import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meeting;
import org.example.coursegetter.entities.baseclasses.Meetings;
import org.example.coursegetter.entities.baseclasses.ScheduleEntry;
import org.example.studentdata.entities.CourseChoice;
import org.example.timetable.entities.Timetable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class CourseBackToBackChecker {

    public CourseTimeslots addSemesterToTimetable(Collection<CourseChoice> semesterOfCourses, CourseTimeslots semesterTimetable) {
        for (CourseChoice course : semesterOfCourses) {
            addCoursesToTimeslot(course, semesterTimetable);
        }
        return semesterTimetable;

    }

    public boolean checkTimetableCourseBackToBack(Timetable studentTimetable) {
        Collection<CourseChoice> fallCourses = studentTimetable.getPlannedCourses("F");
        Collection<CourseChoice> winterCourses = studentTimetable.getPlannedCourses("S");
        Collection<CourseChoice> yearCourses = studentTimetable.getPlannedCourses("Y");
        Collection<CourseChoice> fallAndYearCourses = combineCourses(yearCourses, fallCourses);
        Collection<CourseChoice> winterAndYearCourses = combineCourses(yearCourses, winterCourses);
        if (checkSemesterBackToBack(fallAndYearCourses)) {
            return true;
        } else {
            return checkSemesterBackToBack(winterAndYearCourses);
        }
    }

    public Collection<CourseChoice> combineCourses(Collection<CourseChoice> yearCourses, Collection<CourseChoice> regularCourses) {
        regularCourses.addAll(yearCourses);
        return regularCourses;
    }


    public Boolean checkSemesterBackToBack(Collection<CourseChoice> semesterOfCourses) {
        CourseTimeslots semesterArray = new CourseTimeslots();
        semesterArray = addSemesterToTimetable(semesterOfCourses, semesterArray);
        for (int row = 0; row < semesterArray.getLength(); row++) {
            ScheduleEntry[] dayTimetable = semesterArray.getDay(row);
            for (int col = 0; col < dayTimetable.length; col++) {
                if (dayTimetable[col] != null && col + 1 < dayTimetable.length) {
                    if (dayTimetable[col + 1] != null) {
                        return true;
                    }
                }
            }
        }
        return false;


    }

    public void addCoursesToTimeslot(CourseChoice Course1, CourseTimeslots semesterTimetable) {
        String lectureCode = Course1.getLectureSection();
        String tutorialCode = Course1.getTutSection();
        String practicalCode = Course1.getPraSection();
        Course c1Course = Course1.getCourse();
        Meetings c1Meetings = c1Course.getMeetings();
        Map<String, Meeting> c1Tutorials = c1Meetings.getTutorials();
        Map<String, Meeting> c1Lectures = c1Meetings.getLectures();
        Map<String, Meeting> c1Practicals = c1Meetings.getPracticals();
        Meeting c1Lecture = c1Lectures.get(lectureCode);
        Meeting c1Tutorial = c1Tutorials.get(tutorialCode);
        Meeting c1Practical = c1Practicals.get(practicalCode);
        Set<ScheduleEntry> c1LectureSchedule = c1Lecture.getScheduleEntries();
        Set<ScheduleEntry> c1TutorialSchedule = c1Tutorial.getScheduleEntries();
        Set<ScheduleEntry> c1PracticalSchedule = c1Practical.getScheduleEntries();

        for (ScheduleEntry timeslot : c1LectureSchedule) {
            semesterTimetable.addCourseTimeslots(timeslot);
        }
        for (ScheduleEntry timeslot : c1TutorialSchedule) {
            semesterTimetable.addCourseTimeslots(timeslot);
        }
        for (ScheduleEntry timeslot : c1PracticalSchedule) {
            semesterTimetable.addCourseTimeslots(timeslot);
        }

    }

}
