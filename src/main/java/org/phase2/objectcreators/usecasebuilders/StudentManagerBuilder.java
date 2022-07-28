package org.phase2.objectcreators.usecasebuilders;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import org.example.logincode.usecases.StorageManager;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.CourseSearchAdapterPrev;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentManagerBuilder {

    private Student2 student;

    public CourseSearchAdapter getPlannedSearcher() {
        return plannedSearcher;
    }

    public CourseSearchAdapterPrev getPastSearcher() {
        return pastSearcher;
    }

    private CourseSearchAdapter plannedSearcher;
    private CourseSearchAdapterPrev pastSearcher;


    public StudentManagerBuilder() { }

    public StudentManagerBuilder(String username, StorageManager sm) {
        AccountStorage a = sm.getAccountStorage();
        Account temp = a.getAccount(username);
        this.student = temp.getStudent();
    }

    public StudentManager getStudentManager() {
        return new StudentManager(this.student, this.plannedSearcher, this.pastSearcher);
    }

    public void buildSearcher() {
        this.plannedSearcher = new CourseSearchAdapter();
    }

    public void buildPastSearcher() {
        this.pastSearcher = new CourseSearchAdapterPrev();
    }
}
