package org.example.objectcreators.controllerfactories;

import org.example.htmltables.TableOrganizer;
import org.example.studentrelated.controllers.StudentController;
import org.example.studentrelated.usecases.StudentManager;
import org.example.studentrelated.usecases.WarningChecker2;

public class StudentControllerBuilder {

    private final StudentManager manager;
    private final TableOrganizer fTable;
    private final TableOrganizer sTable;
    private final WarningChecker2 wc;

    public StudentControllerBuilder(StudentManager sm2, WarningChecker2 wc) {
        this.manager = sm2;
        this.wc = wc;
        fTable = new TableOrganizer('F', wc);
        sTable = new TableOrganizer('S', wc);
    }

    public StudentController getController() {
        return new StudentController(this.manager, fTable, sTable, wc);
    }

}
