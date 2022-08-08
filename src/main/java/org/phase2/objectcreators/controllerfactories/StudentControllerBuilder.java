package org.phase2.objectcreators.controllerfactories;

import org.phase2.htmltables.TableOrganizer;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

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
