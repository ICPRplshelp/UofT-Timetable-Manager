package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.usecases.StorageManager;
import org.phase2.htmltables.TableOrganizer;
import org.phase2.objectcreators.usecasebuilders.StudentManagerBuilder;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentControllerBuilder implements ControllerBuilder {

    private final StudentManager manager;
    private final TableOrganizer fTable;
    private final TableOrganizer sTable;


    public StudentControllerBuilder(String username, StorageManager sm) {
        StudentManagerBuilder builder = new StudentManagerBuilder(username,
                sm);
        builder.buildSearcher();
        builder.buildPastSearcher();
        this.manager = builder.getStudentManager();
        fTable = new TableOrganizer('F');
        sTable = new TableOrganizer('S');
    }

    @Override
    public StudentController getController() {
        return new StudentController(this.manager, fTable, sTable);
    }

}
