package org.example.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerAdmin;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher;
import org.example.logincode.controllerspresentersgateways.controllers.ControllerStandard;
import org.example.logincode.usecases.StorageManager;
import org.example.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.example.objectcreators.usecasebuilders.StudentManagerBuilder;
import org.example.studentrelated.controllers.StudentController;
import org.example.studentrelated.usecases.StudentManager;
import org.example.studentrelated.usecases.WarningChecker;

public class ControllerFactory {

    private final StorageManager sm;
    private final String username;
    private StudentManager manager;
    private ControllerAdmin controllerAdmin;
    private ControllerStandard controllerStandard;
    private StudentController studentController;
    private ControllerCourseSearcher controllerCourseSearcher;
    private WarningChecker wc;

    public ControllerFactory(String username, MAccountLoginValidator malv) {
        this.sm = malv.getStorageManager();
        this.username = username;
    }

    /**
     * Obtains the respective controller. May be affected by
     * the username of the user that is logged in, and the
     * account validator, which contains the storage.
     *
     * @return the respective controller. It is never null.
     */
    public ControllerAdmin getControllerAdmin() {
        if (this.controllerAdmin == null) buildControllerAdmin();
        return this.controllerAdmin;
    }

    /**
     * Obtains the respective controller. May be affected by
     * the username of the user that is logged in, and the
     * account validator, which contains the storage.
     *
     * @return the respective controller. It is never null.
     */
    public ControllerStandard getControllerStandard() {
        if (this.controllerStandard == null) buildControllerStandard();
        return this.controllerStandard;
    }

    /**
     * Obtains the respective controller. May be affected by
     * the username of the user that is logged in, and the
     * account validator, which contains the storage.
     *
     * @return the respective controller. It is never null.
     */
    public StudentController getStudentController() {
        if (this.studentController == null) buildStudentController();
        return this.studentController;
    }

    /**
     * Obtains the respective controller. May be affected by
     * the username of the user that is logged in, and the
     * account validator, which contains the storage.
     *
     * @return the respective controller. It is never null.
     */
    public ControllerCourseSearcher getSearchController() {
        if (this.controllerCourseSearcher == null)
            buildSearchController();
        return this.controllerCourseSearcher;
    }

    /**
     * Builds the respective controller and stores
     * it as an instance attribute.
     */
    private void buildControllerAdmin() {
        ControllerAdminBuilder adminBuilder = new ControllerAdminBuilder(username, sm);
        this.controllerAdmin = adminBuilder.getController();
    }

    /**
     * Builds the respective controller and stores
     * it as an instance attribute.
     */
    private void buildControllerStandard() {
        ControllerStandardBuilder standardBuilder = new ControllerStandardBuilder(username, sm);
        this.controllerStandard = standardBuilder.getController();
    }

    /**
     * Builds the respective controller and stores
     * it as an instance attribute.
     */
    private void buildStudentController() {
        StudentControllerBuilder scBuilder = new StudentControllerBuilder(
                getStudentManager(), getWarningChecker());

        this.studentController = scBuilder.getController();
    }

    /**
     * Gets the warning checker.
     * @return the warning checker. It is never null.
     */
    private WarningChecker getWarningChecker() {
        StudentManager manager = getStudentManager();
        if (this.wc == null) {
            this.wc = new WarningChecker(manager.getCSA(),
                    manager.getPlannedCourses(), manager.getPassedCourses());
        }
        return this.wc;
    }

    /**
     * Returns the student manager based on the username and
     * the course storage, which contains getters for both course
     * searcher adapter classes.
     *
     * @return as stated in the description. It is never null.
     */
    private StudentManager getStudentManager() {
        if (this.manager == null) {
            StudentManagerBuilder builder = new StudentManagerBuilder(username,
                    sm);
            builder.buildSearcher();
            builder.buildPastSearcher();
            this.manager = builder.getStudentManager();
        }
        return this.manager;
    }


    /**
     * Builds the respective controller and stores
     * it as an instance attribute.
     */
    private void buildSearchController() {
        ControllerCourseSearcherBuilder searcher2builder = new ControllerCourseSearcherBuilder(
                getWarningChecker());
        this.controllerCourseSearcher = searcher2builder.getController();
    }
}
