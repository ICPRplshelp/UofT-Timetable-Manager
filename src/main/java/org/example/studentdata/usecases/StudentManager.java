package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager {

    private final Student student;

    public StudentManager(Student student) {
        this.student = student;
    }

    // TODO: implement (alex + hanmin?)
    // I'm gonna make this better soon
    public boolean addPlannedCourse(List<CourseChoice> courseList) {
        student.addToPlannedCourses(courseList);
        return true;
    }

    public boolean removePlannedCourse(List<CourseChoice> courseList) {
        student.removeFromPlannedCourses(courseList);
        return true;
    }

    public boolean addPreviousCourse(Collection<CourseChoice> courseList) {
        student.addToPreviousCourses(courseList.stream()
                .map(CourseChoice::getCourse).toList());
        return true;
    }

    public boolean removePreviousCourse(Collection<CourseChoice> courseList) {
        student.removeFromPreviousCourses(courseList.stream()
                .map(CourseChoice::getCourse).toList());
        return true;
    }

    public boolean specifySection(Course course, String lecSection, String tutSection, String praSection) {
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
