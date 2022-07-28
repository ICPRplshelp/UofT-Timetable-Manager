package org.phase2.controllerfactories;

import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;

public class StudentControllerBuilder implements ControllerBuilder{

    private StudentManager manager;

    public StudentControllerBuilder(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public StudentController getController() {
        return new StudentController(this.manager);
    }

}
