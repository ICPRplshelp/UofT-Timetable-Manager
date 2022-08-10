package org.example.objectcreators.usecasebuilders;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import org.example.logincode.usecases.StorageManager;
import org.example.studentrelated.entities.Student;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.example.studentrelated.usecases.StudentManager;

public class StudentManagerBuilder {

    private final Student student;

    private UsableCourseSearcher plannedSearcher;
    private UsableCourseSearcherPrev pastSearcher;


    public StudentManagerBuilder(String username, StorageManager sm) {
        AccountStorage a = sm.getAccountStorage();
        Account temp = a.getAccount(username);
        this.student = temp.getStudent();
    }

    public StudentManager getStudentManager() {
        return new StudentManager(this.student, this.plannedSearcher, this.pastSearcher);
    }

    public void buildSearcher() {
        this.plannedSearcher = UsableCourseSearcher.getInstance();
    }

    public void buildPastSearcher() {
        this.pastSearcher = UsableCourseSearcherPrev.getInstance();
    }
}
