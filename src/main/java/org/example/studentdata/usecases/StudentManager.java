package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;
import org.example.timetable.entities.Timetable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

    public boolean addBlankPlannedCourse(Course course) {
        if (course == null){
            return false;
        }
        addPlannedCourse(new CourseChoice(course));
        return true;
    }

    public boolean removePlannedCourse(CourseChoice courseChoice) {

        removePlannedCourses(List.of(courseChoice));
        return true;
    }

    public CourseChoice getCourseChoice(Course course) {
        Collection<CourseChoice> courseChoiceList = student.getPlannedCourses();
        for (CourseChoice courseChoice: courseChoiceList) {
            if (courseChoice.getCourse() == course) {
                return courseChoice;
            }
        }
        return null;
    }

    public boolean setCourseChoice(CourseChoice course, String section) {
        String prefix = section.substring(0, Math.min(section.length(), 3));
        String number = section.substring(Math.min(section.length(), 3), Math.min(section.length(), 7));
        switch (prefix.toLowerCase()) {
            case "lec" -> course.setLectureSection("LEC" + number);
            case "tut" -> course.setTutSection("TUT" + number);
            case "pra" -> course.setPraSection("PRA" + number);
            default -> { return false;}
        }
        return true;
    }

    public boolean removePlannedCourses(List<CourseChoice> courseList) {
        student.removeFromPlannedCourses(courseList);
        return true;
    }

    public boolean addPreviousCourses(Collection<Course> courseList) {
        student.addToPreviousCourses(courseList);
        return true;
    }

    public boolean addPreviousCourse(Course course){
        if (course == null) return false;
        student.addToPreviousCourses(List.of(course));
        return true;
    }

    public boolean removePreviousCourses(Collection<Course> courseList) {
        student.removeFromPreviousCourses(courseList);
        return true;
    }
    public boolean removePreviousCourse(Course course){
        if (course == null) return false;
        student.removeFromPreviousCourses(List.of(course));
        return true;
    }

    public boolean checkPlannedCourseExists(Course course) {
        return (student.getPlannedCourses().stream()
                .map(CourseChoice::getCourse).toList().contains(course));
    }

    public String getCourseHistory() {
        List<String> coursesList = student.getAllPreviousCourses().stream()
                .map(Course::getOfferingCode).toList();
        return String.join(", ", coursesList);
    }

    public CourseChoice getPlannedCourseByString(String courseCode) {
        for (CourseChoice course : student.getPlannedCourses()) {
            if (courseCode.equals(course.getCourse().getOfferingCode())) {
                return course;
            }
        }
        return null;
    }

    public Course getPreviousCourseByString(String courseCode) {
        for (Course course : student.getAllPreviousCourses()) {
            if (courseCode.equals(course.getCode())) {
                return course;
            }
        }
        return null;
    }


}
