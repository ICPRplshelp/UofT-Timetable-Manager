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

    public UIInput2 getInputAdmin() {
        return new UIInputAdmin2(prt, cf.getControllerAdmin(), cmdl);
    }

    public UIInput2 getInputCourseSearch() {
        return new UIInputCourseSearch2(prt, cf.getSearchController(), cmdl);
    }

    public UIInput2 getInputStandard() {
        return new UIInputStandard2(prt, cf.getControllerStandard(), cmdl);
    }

    public UIInput2 getInputStudent() {
        return new UIInputStudent2(prt, cf.getStudentController(), cmdl);
    }

}
