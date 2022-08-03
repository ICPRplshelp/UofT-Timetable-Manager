package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.controllerspresentersgateways.controllers.ControllerCourseSearcher2;
import org.phase2.studentrelated.usecases.CourseSearcher;
import org.phase2.studentrelated.usecases.CourseSearcherPrev;
import org.phase2.studentrelated.usecases.WarningChecker2;

public class ControllerCourseSearcher2Builder implements ControllerBuilder {

    private CourseSearcher csa;

    private CourseSearcherPrev pcsa;
    private final WarningChecker2 wc;

    public ControllerCourseSearcher2Builder(WarningChecker2 wc) {
        this.wc = wc;
    }

    @Override
    public ControllerCourseSearcher2 getController() {
        buildCourseSearchAdapter();
        buildCourseSearchAdapterPrev();

        return new ControllerCourseSearcher2(this.csa, this.pcsa, wc);
    }

    private void buildCourseSearchAdapter() {
        this.csa = new CourseSearcher();
    }

    private void buildCourseSearchAdapterPrev() {
        this.pcsa = new CourseSearcherPrev();
    }
}
