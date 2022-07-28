package org.phase2.objectcreators.controllerfactories;

import org.example.logincode.interfaceadapters.controllers.ControllerCourseSearcher2;
import org.phase2.studentrelated.usecases.CourseSearchAdapter;
import org.phase2.studentrelated.usecases.CourseSearchAdapterPrev;

public class ControllerCourseSearcher2Builder implements ControllerBuilder{

    private CourseSearchAdapter csa;

    private CourseSearchAdapterPrev pcsa;


    public ControllerCourseSearcher2Builder() {

    }

    @Override
    public ControllerCourseSearcher2 getController() {
        buildCourseSearchAdapter();
        buildCourseSearchAdapterPrev();

        return new ControllerCourseSearcher2(this.csa, this.pcsa);
    }

    private void buildCourseSearchAdapter() {
        this.csa = new CourseSearchAdapter();
    }

    private void buildCourseSearchAdapterPrev() {
        this.pcsa = new CourseSearchAdapterPrev();
    }
}
