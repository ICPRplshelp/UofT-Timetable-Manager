package org.phase2.controllerfactories;

import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.usecasebuilders.StudentManagerBuilder;

public class StudentControllerBuilder implements ControllerBuilder{

    private StudentManager manager;

    public StudentControllerBuilder() {

    }

    @Override
    public StudentController getController() {
        return new StudentController(this.manager);
    }

    public void buildManager() {
        StudentManagerBuilder smBuilder = new StudentManagerBuilder();

        smBuilder.buildStudent();
        smBuilder.buildSearcher();
        smBuilder.buildPastSearcher();
        this.manager = smBuilder.getStudentManager();
    }

//    this one breaks clean architecture
//    public void buildManager(Student2 s) {
//        StudentManagerBuilder smBuilder = new StudentManagerBuilder();
//
//        if (s == null) {
//            smBuilder.buildStudent();
//        } else {
//            smBuilder.buildStudent(s);
//        }
//
//        smBuilder.buildSearcher();
//        smBuilder.buildPastSearcher();
//        this.manager = smBuilder.getStudentManager();
//    }

}
