package org.phase2.objectcreators.usecasebuilders;

import org.example.logincode.entities.Account;
import org.example.logincode.entities.AccountStorage;
import org.example.logincode.usecases.StorageManager;
import org.phase2.studentrelated.entities.Student2;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcher;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentManagerBuilder {

    private Student2 student;

    public UsableCourseSearcher getPlannedSearcher() {
        return plannedSearcher;
    }

    public UsableCourseSearcherPrev getPastSearcher() {
        return pastSearcher;
    }

    private UsableCourseSearcher plannedSearcher;
    private UsableCourseSearcherPrev pastSearcher;


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
        this.plannedSearcher = UsableCourseSearcher.getInstance();
    }

    public void buildPastSearcher() {
        this.pastSearcher = UsableCourseSearcherPrev.getInstance();
    }
}
