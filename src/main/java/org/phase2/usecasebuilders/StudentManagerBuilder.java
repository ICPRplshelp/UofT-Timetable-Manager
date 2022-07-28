package org.phase2.usecasebuilders;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.CourseSearchAdapterPrev;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentManagerBuilder {

    private Student2 student;
    private CourseSearchAdapter plannedSearcher;
    private CourseSearchAdapterPrev pastSearcher;


    public StudentManagerBuilder() { }

    public StudentManagerBuilder(String username) {
        AccountStorage a = new AccountStorage();
        Account temp = a.getAccount(username);
        this.student = temp.getStudent();
    }

    public StudentManager getStudentManager() {
        return new StudentManager(this.student, this.plannedSearcher, this.pastSearcher);
    }

    public void buildStudent(Student2 student) { this.student = student; }

    public void buildStudent() {
        this.student = new Student2();
    }

    public void buildSearcher() {
        this.plannedSearcher = new CourseSearchAdapter();
    }

    public void buildPastSearcher() {
        this.pastSearcher = new CourseSearchAdapterPrev();
    }
}
