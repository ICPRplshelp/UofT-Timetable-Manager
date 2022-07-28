package org.phase2.controllerfactories;

import org.example.logincode.interfaceadapters.controllers.ControllerAdmin;
import org.example.logincode.interfaceadapters.controllers.ControllerCourseSearcher2;
import org.example.logincode.interfaceadapters.controllers.ControllerStandard;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;

public class ControllerFactory {

//    private String username;
    private StudentManager manager;

    private ControllerAdmin controllerAdmin;
    private ControllerStandard controllerStandard;
    private StudentController studentController;
    private ControllerCourseSearcher2 controllerCourseSearcher2;


    public ControllerFactory(StudentManager manager) {
        this.manager = manager;
    }

    public ControllerFactory() {
    }

    public ControllerAdmin getControllerAdmin() {
        return this.controllerAdmin;
    }

    public ControllerStandard getControllerStandard() {
        return this.controllerStandard;
    }

    public StudentController getStudentController() {
        return this.studentController;
    }

    public ControllerCourseSearcher2 getSearchController() {
        if (this.controllerCourseSearcher2 == null) {
            buildSearchController();
        }
        return this.controllerCourseSearcher2;
    }



    public void buildControllerAdmin(String username) {
        ControllerAdminBuilder adminBuilder = new ControllerAdminBuilder(username);
        this.controllerAdmin = adminBuilder.getController();
    }

    public void buildControllerStandard(String username) {
        ControllerStandardBuilder standardBuilder = new ControllerStandardBuilder(username);
        this.controllerStandard = standardBuilder.getController();
    }

    public void buildStudentController() {
        StudentControllerBuilder scBuilder = new StudentControllerBuilder(manager);
        this.studentController = scBuilder.getController();
    }

    public void buildStudentController(String username) {
        StudentControllerBuilder scBuilder = new StudentControllerBuilder(username);
        this.studentController = scBuilder.getController();
    }

    public void buildSearchController() {
        ControllerCourseSearcher2Builder searcher2builder = new ControllerCourseSearcher2Builder();
        this.controllerCourseSearcher2 = searcher2builder.getController();
    }

}
