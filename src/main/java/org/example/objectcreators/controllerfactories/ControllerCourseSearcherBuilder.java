package org.example.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.usecases.WarningChecker;

public class ControllerCourseSearcherBuilder {

    private UsableCourseSearcher csa;

    private final WarningChecker wc;

    public ControllerCourseSearcherBuilder(WarningChecker wc) {
        this.wc = wc;
    }

    public ControllerCourseSearcher getController() {
        buildCourseSearchAdapter();

        return new ControllerCourseSearcher(this.csa, wc);
    }

    private void buildCourseSearchAdapter() {
        this.csa = UsableCourseSearcher.getInstance();
    }
}
