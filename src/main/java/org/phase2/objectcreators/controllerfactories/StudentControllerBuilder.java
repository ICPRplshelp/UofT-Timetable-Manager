package org.phase2.objectcreators.controllerfactories;

import org.phase2.objectcreators.usecasebuilders.StudentManagerBuilder;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentControllerBuilder implements ControllerBuilder{

    private StudentManager manager;

    public StudentControllerBuilder(StudentManager manager) {
        this.manager = manager;
    }

    public StudentControllerBuilder(String username) {
        StudentManagerBuilder builder = new StudentManagerBuilder(username);
        builder.buildSearcher();
        builder.buildPastSearcher();
        this.manager = builder.getStudentManager();
    }

    @Override
    public StudentController getController() {
        return new StudentController(this.manager);
    }

}
