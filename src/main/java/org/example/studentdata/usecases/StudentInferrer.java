package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
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
    public double getCreditCount(){
        double creditsSoFar = 0.0;
        for (Course a : student.getAllPreviousCourses()) {
            creditsSoFar += a.getCreditValue();
        }
        return creditsSoFar;
    }

    /**
     * @return student's credit count after the session, counting
     * planned courses.
     */
    public double getPlannedCreditCount(){
        double creditsSoFar = getCreditCount();
        for(CourseChoice crs : student.getPlannedCourses()){
            creditsSoFar += crs.getCourse().getCreditValue();
        }
        return creditsSoFar;

    }

    public int getYearOfStudy(){
        int halfCreditCount = (int) Math.round(getCreditCount() * 2);
        if (halfCreditCount < 8) return 1;
        if (halfCreditCount < 18) return 2;
        if (halfCreditCount < 28) return 3;
        else return 4;
    }
}