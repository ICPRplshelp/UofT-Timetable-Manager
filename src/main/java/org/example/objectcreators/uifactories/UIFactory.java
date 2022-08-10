package org.example.objectcreators.uifactories;

import org.example.PresenterPrinter;
import org.example.logincode.uiinput.*;
import org.example.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.example.mainloophelpers.controllerspresenters.UICommandList;
import org.example.objectcreators.controllerfactories.ControllerFactory;

public class UIFactory {

    private final ControllerFactory cf;
    private final PresenterPrinter prt;
    private final UICommandList cmdl;

    public UIFactory(String username, MAccountLoginValidator malv) {
        this.cf = new ControllerFactory(username, malv);
        this.prt = new PresenterPrinter();
        this.cmdl = new UICommandList();
    }

    /**
     * Obtains the respective UI Input.
     * Uses the ControllerAdmin instance
     * obtained from ControllerFactory
     *
     * @return the respective UI Input.
     */
    public UIInput getInputAdmin() {
        return new UIInputAdmin(prt, cf.getControllerAdmin(), cmdl);
    }

    /**
     * Obtains the respective UI Input.
     * Uses the ControllerCourseSearch instance
     * obtained from ControllerFactory
     *
     * @return the respective UI Input.
     */
    public UIInput getInputCourseSearch() {
        return new UIInputCourseSearch(prt, cf.getSearchController(), cmdl);
    }

    /**
     * Obtains the respective UI Input.
     * Uses the ControllerStandard instance
     * obtained from ControllerFactory
     *
     * @return the respective UI Input.
     */
    public UIInput getInputStandard() {
        return new UIInputStandard(prt, cf.getControllerStandard(), cmdl);
    }

    /**
     * Obtains the respective UI Input.
     * Uses the StudentController instance
     * obtained from ControllerFactory
     *
     * @return the respective UI Input.
     */
    public UIInput getInputStudent() {
        return new UIInputStudent(prt, cf.getStudentController(), cmdl);
    }

}
