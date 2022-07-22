package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.Student;

public class StudentInferrer {
    private final Student student;

    public StudentInferrer(Student student) {
        this.student = student;
    }

    /**
     * @return student's credit count before the session, not counting
     * planned courses.
     */
    public double getCreditCount() {
        double creditsSoFar = 0.0;
        for (Course a : student.getAllPreviousCourses()) {
            creditsSoFar += a.getCreditValue();
        }
        return creditsSoFar;
    }

}
