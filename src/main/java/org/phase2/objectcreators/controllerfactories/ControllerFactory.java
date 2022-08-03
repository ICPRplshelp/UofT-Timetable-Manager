package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerAdmin;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;
import org.example.logincode.usecases.StorageManager;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.phase2.studentrelated.controllers.StudentController;
import org.phase2.studentrelated.usecases.StudentManager;
import org.phase2.studentrelated.usecases.WarningChecker2;

public class ControllerFactory {

    //    private String username;
    private StudentManager manager;

    private ControllerAdmin controllerAdmin;
    private ControllerStandard controllerStandard;
    private StudentController studentController;
    private ControllerCourseSearcher2 controllerCourseSearcher2;

    private final StorageManager sm;
    private final String username;


    public ControllerFactory(String username, MAccountLoginValidator malv) {
        this.sm = malv.getStorageManager();
        this.username = username;
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


    public void buildControllerAdmin() {
        ControllerAdminBuilder adminBuilder = new ControllerAdminBuilder(username, sm);
        this.controllerAdmin = adminBuilder.getController();
    }

    public void buildControllerStandard() {
        ControllerStandardBuilder standardBuilder = new ControllerStandardBuilder(username, sm);
        this.controllerStandard = standardBuilder.getController();
    }

    public void buildStudentController() {
        StudentControllerBuilder scBuilder = new StudentControllerBuilder(username, sm);

        this.studentController = scBuilder.getController();
    }

    public void buildSearchController() {
        StudentController stc = getStudentController();
        if(stc == null) {
            buildStudentController();
            stc = getStudentController();
        }
        WarningChecker2 temp = stc.getWc();
        ControllerCourseSearcher2Builder searcher2builder = new ControllerCourseSearcher2Builder(temp);
        this.controllerCourseSearcher2 = searcher2builder.getController();
    }
}
