package org.example.timetable.usecases;

import org.example.logincode.usecases.AccountManager;
import org.example.studentdata.entities.Student;
import org.example.studentdata.usecases.StudentInferrer;
import org.example.timetable.entities.Timetable;


public class TimetableCommunicatorBulkBuilder {
    /**
     * Builds the TimetableCommunicatorBulk.
     *
     * @param accm the account manager from the controller.
     * @return the associated TimetableCommuniatorBulk.
     */
    public TimetableCommunicatorBulk buildit(AccountManager accm) {
        Student stud = accm.getAccount().getStudent();
        Timetable timetable = stud.getTimetable();

        StudentInferrer st = new StudentInferrer(stud);
        timetable.setPreviousCredits(st.getCreditCount());

        timetable.setPreviousCourses(stud.getAllPreviousCourses());

        return new TimetableCommunicatorBulk(timetable);
    }
}
