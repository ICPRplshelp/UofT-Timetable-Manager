package org.phase2.objectcreators.uifactories;

import org.example.PresenterPrinter;
import org.example.logincode.uiinput.*;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;
import org.phase2.objectcreators.controllerfactories.ControllerFactory;

public class UIFactory {

    private final String username;
    private final ControllerFactory cf;
    private final PresenterPrinter prt;
    private final UICommandList cmdl;

    public UIFactory(String username, MAccountLoginValidator malv) {
        this.username = username;
        this.cf = new ControllerFactory(username, malv);
        this.prt = new PresenterPrinter();
        this.cmdl = new UICommandList();
    }

    public UIInput2 getInputAdmin() {
        cf.buildControllerAdmin(username);
        return new UIInputAdmin2(prt, cf.getControllerAdmin(), cmdl);
    }

    public UIInput2 getInputCourseSearch() {
        cf.buildSearchController();
        return new UIInputCourseSearch2(prt, cf.getSearchController(), cmdl);
    }

    public UIInput2 getInputStandard() {
        cf.buildControllerStandard(username);
        return new UIInputStandard2(prt, cf.getControllerStandard(), cmdl);
    }

    public UIInput2 getInputStudent() {
        cf.buildStudentController(username);
        return new UIInputStudent2(prt, cf.getStudentController(), cmdl);
    }

}
