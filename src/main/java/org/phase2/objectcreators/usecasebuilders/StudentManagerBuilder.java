package org.phase2.objectcreators.usecasebuilders;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import org.example.logincode.usecases.StorageManager;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.usecases.CourseSearcher;
import org.phase2.studentrelated.usecases.CourseSearcherPrev;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentManagerBuilder {

    private Student2 student;

    public CourseSearcher getPlannedSearcher() {
        return plannedSearcher;
    }

    public CourseSearcherPrev getPastSearcher() {
        return pastSearcher;
    }

    private CourseSearcher plannedSearcher;
    private CourseSearcherPrev pastSearcher;


    public StudentManagerBuilder() {
    }

    public StudentManagerBuilder(String username, StorageManager sm) {
        AccountStorage a = sm.getAccountStorage();
        Account temp = a.getAccount(username);
        this.student = temp.getStudent();
    }

    public StudentManager getStudentManager() {
        return new StudentManager(this.student, this.plannedSearcher, this.pastSearcher);
    }

    public void buildSearcher() {
        this.plannedSearcher = new CourseSearcher();
    }

    public void buildPastSearcher() {
        this.pastSearcher = new CourseSearcherPrev();
    }
}
