package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.Student;

public class StudentManager {

    private final Student student;

    public StudentManager(Student student) {
        this.student = student;
    }

    // TODO: implement (alex + hanmin?)
    public boolean addCourse(Course course) {
        return true;
    }

    public boolean removeCourse(Course course) {
        return true;
    }

    public boolean specifySection(Course course, String sectionType, String sectionNum) {
        return true;
    }

    public String getCourseHistory() {
        return "placeholder";
    }
}
