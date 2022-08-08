package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcher;
import org.phase2.studentrelated.searchersusecases.UsableCourseSearcherPrev;
import org.phase2.studentrelated.usecases.WarningChecker2;

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
