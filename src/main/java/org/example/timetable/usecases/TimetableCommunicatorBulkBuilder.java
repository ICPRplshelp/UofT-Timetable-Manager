package org.example.timetable.usecases;

import org.example.logincode.usecases.AccountManager;
import org.example.studentdata.entities.Student;
import org.example.timetable.entities.Timetable;

public class TimetableCommunicatorBulkBuilder {
    public TimetableCommunicatorBulk buildit(AccountManager accm){
        Student stud = accm.getAccount().getStudent();
        Timetable timetable = stud.getTimetable();
        return new TimetableCommunicatorBulk(timetable);
    }
}
