package org.example.coursegetter;

import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.coursegetter.usecases.CourseSearcherGetter;

/**
 * Create a course searcher. Now you can use it whenever you want.
 */
public class CourseMiniController {

    private final CourseSearcherIndividual courseSearcherIndividual;


    public CourseMiniController() {
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        courseSearcherIndividual = csgTemp.getCourseSearcher();
    }


}
