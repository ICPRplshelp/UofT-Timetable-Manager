package org.phase2.objectcreators.uifactories;

import org.example.PresenterPrinter;
import org.example.logincode.uiinput.*;
import org.phase2.objectcreators.controllerfactories.ControllerFactory;

public class UIFactory {

    private final String username;
    private final ControllerFactory cf;
    private final PresenterPrinter prt;

    public UIFactory(String username) {
        this.username = username;
        this.cf = new ControllerFactory();
        this.prt = new PresenterPrinter();
    }

    public UIInput2 getInputAdmin() {
        cf.buildControllerAdmin(username);
        return new UIInputAdmin2(prt, cf.getControllerAdmin());
    }

    public UIInput2 getInputCourseSearch() {
        cf.buildSearchController();
        return new UIInputCourseSearch2(prt, cf.getSearchController());
    }

    public UIInput2 getInputStandard() {
        cf.buildControllerStandard(username);
        return new UIInputStandard2(prt, cf.getControllerStandard());
    }

    public UIInput2 getInputStudent() {
        cf.buildStudentController(username);
        return new UIInputStudent2(prt, cf.getStudentController());
    }

}
