package org.example.objectcreators.uifactories;


import org.example.gui.AdminUI;
import org.example.gui.CourseSearchUI;
import org.example.gui.StandardUI;
import org.example.gui.StudentUI;
import org.example.mainloophelpers.controllerspresenters.MAccountLoginValidator;
import org.example.objectcreators.controllerfactories.ControllerFactory;

import javax.swing.*;

public class GUIFactory {

    private final ControllerFactory cf;

    public GUIFactory(String username, MAccountLoginValidator malv) {
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
