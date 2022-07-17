package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;
import org.example.timetable.entities.Timetable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager {

    private final Student student;

    public StudentManager(Student student) {
        this.student = student;
    }

    public Timetable getTimetable() {
        return student.getTimetable();
    }

    // TODO: implement (alex + hanmin?)
    // I'm gonna make this better soon
    // THE FOLLOWING METHODS ARE TO BE DELETED ONCE TIMETABLEMANAGER IS FINALIZED.
    public boolean addPlannedCourse(List<CourseChoice> courseList) {
        student.addToPlannedCourses(courseList);
        return true;
    }
    public boolean addPlannedCourse(CourseChoice courseChoice) {
        student.addToPlannedCourses(List.of(courseChoice));
        return true;
    }

    public boolean removePlannedCourse(CourseChoice courseChoice) {

        removePlannedCourses(List.of(courseChoice));
        return true;
    }



    public boolean removePlannedCourses(List<CourseChoice> courseList) {
        student.removeFromPlannedCourses(courseList);
        return true;
    }



    public boolean addPreviousCourses(Collection<CourseChoice> courseList) {
        student.addToPreviousCourses(courseList.stream()
                .map(CourseChoice::getCourse).toList());
        return true;
    }

    public boolean addPreviousCourse(CourseChoice course){
        if (course == null) return false;
        student.addToPreviousCourses(List.of(course.getCourse()));
        return true;
    }

    public boolean removePreviousCourses(Collection<CourseChoice> courseList) {
        student.removeFromPreviousCourses(courseList.stream()
                .map(CourseChoice::getCourse).toList());
        return true;
    }
    public boolean removePreviousCourse(CourseChoice course){
        if (course == null) return false;
        student.removeFromPreviousCourses(List.of(course.getCourse()));
        return true;
    }

    public String getPlannedCourses(String session) {
        Collection<CourseChoice> returnCourses;
        switch (session) {
            case "F" -> returnCourses = student.getPlannedFCourses();
            case "S" -> returnCourses = student.getPlannedSCourses();
            case "Y" -> returnCourses = student.getPlannedYCourses();
            default -> {
                returnCourses = student.getPlannedFCourses();
                returnCourses.addAll(student.getPlannedSCourses());
                returnCourses.addAll(student.getPlannedYCourses()); }
        }

        Collection<String> coursesList = toCourseNameHelper(returnCourses);
        return String.join(", ", coursesList);

    }

    private Collection<String> toCourseNameHelper(Collection<CourseChoice> coursesList) {
        return coursesList.stream()
                .map(CourseChoice::getCourse).toList().stream()
                .map(Course::getOfferingCode)
                .collect(Collectors.toList());
    }
    public String getCourseHistory() {
        List<String> coursesList = student.getAllPreviousCourses().stream()
                .map(Course::getOfferingCode).toList();
        return String.join(", ", coursesList);
    }


}
