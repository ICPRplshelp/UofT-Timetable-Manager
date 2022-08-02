package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.usecases.StorageManager;
import org.phase2.htmltables.TableOrganizer;
import org.phase2.objectcreators.usecasebuilders.StudentManagerBuilder;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

public class StudentControllerBuilder implements ControllerBuilder {

    private final StudentManager manager;
    private final TableOrganizer fTable;
    private final TableOrganizer sTable;
    private final WarningChecker2 wc;

    public StudentControllerBuilder(String username, StorageManager sm) {
        StudentManagerBuilder builder = new StudentManagerBuilder(username,
                sm);
        builder.buildSearcher();
        builder.buildPastSearcher();
        this.manager = builder.getStudentManager();
        this.wc = new WarningChecker2(manager.getCSA(), manager.getCSAP(),
                 this.manager.getPlannedCourses(), this.manager.getPassedCourses());
        fTable = new TableOrganizer('F', wc);
        sTable = new TableOrganizer('S', wc);
    }

    @Override
    public StudentController getController() {
        return new StudentController(this.manager, fTable, sTable, wc
                );
    }

}
