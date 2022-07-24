package org.example.timetable.usecases;

import org.example.logincode.usecases.AccountManager;
import org.example.studentdata.entities.Student;
import org.example.studentdata.usecases.StudentInferrer;
import org.example.timetable.entities.Timetable;


public class TimetableCommunicatorBulkBuilder {
    public TimetableCommunicatorBulk buildit(AccountManager accm) {
        Student stud = accm.getAccount().getStudentOld();
        Timetable timetable = stud.getTimetable();

        StudentInferrer st = new StudentInferrer(stud);
        timetable.setPreviousCredits(st.getCreditCount());

        timetable.setPreviousCourses(stud.getAllPreviousCourses());

        return new TimetableCommunicatorBulk(timetable);
    }
}
