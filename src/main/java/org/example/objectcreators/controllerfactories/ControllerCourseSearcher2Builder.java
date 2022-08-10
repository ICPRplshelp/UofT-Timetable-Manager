package org.example.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.usecases.WarningChecker2;

public class ControllerCourseSearcher2Builder {

    private UsableCourseSearcher csa;

    private final WarningChecker2 wc;

    public ControllerCourseSearcher2Builder(WarningChecker2 wc) {
        this.wc = wc;
    }

    public ControllerCourseSearcher2 getController() {
        buildCourseSearchAdapter();

        return new ControllerCourseSearcher2(this.csa, wc);
    }

    private void buildCourseSearchAdapter() {
        this.csa = UsableCourseSearcher.getInstance();
    }
}
