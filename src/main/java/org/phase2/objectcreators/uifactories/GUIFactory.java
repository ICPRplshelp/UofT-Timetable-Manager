package org.phase2.objectcreators.uifactories;

import org.example.PresenterPrinter;
import org.example.logincode.uiinput.*;
import org.phase2.gui.frameworksanddrivers.AdminUI;
import org.phase2.gui.frameworksanddrivers.CourseSearchUI;
import org.phase2.gui.frameworksanddrivers.StandardUI;
import org.phase2.gui.frameworksanddrivers.StudentUI;
import org.phase2.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.phase2.mainloophelpers.controllerspresenters.UICommandList;
import org.phase2.objectcreators.controllerfactories.ControllerFactory;

import javax.swing.*;

public class GUIFactory {

    private final String username;
    private final ControllerFactory cf;

    public GUIFactory(String username, MAccountLoginValidator malv) {
        this.username = username;
        this.cf = new ControllerFactory(username, malv);
    }

    public JPanel getInputAdmin() {
        return new AdminUI(cf.getControllerAdmin()).getMainPanel();
    }

    public JPanel getInputCourseSearch() {
        return new CourseSearchUI(cf.getSearchController()).getMainPanel();
    }

    public JPanel getInputStandard() {
        return new StandardUI(cf.getControllerStandard()).getMainPanel();
    }

    public JPanel getInputStudent() {
        return new StudentUI(cf.getStudentController()).getMainPanel();
    }

}
