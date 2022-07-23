package org.example.studentdata.usecases;

import org.example.logincode.usecases.AccountManager;

public class StudentManagerBuilder {
    public StudentManager buildStudentManager(AccountManager acm){
        return new StudentManager(acm.getAccount().getStudentOld());

    }
}
