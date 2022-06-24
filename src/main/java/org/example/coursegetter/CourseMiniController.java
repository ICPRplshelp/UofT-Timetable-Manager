package org.example.coursegetter;

import org.example.coursegetter.usecases.internal.CourseSearcher;
import org.example.coursegetter.usecases.CourseSearcherGetter;

/**
 * Create a course searcher. Now you can use it whenever you want.
 */
public class CourseMiniController {

    private final CourseSearcher courseSearcher;


    public CourseMiniController() {
        CourseSearcherGetter csgTemp = new CourseSearcherGetter();
        courseSearcher = csgTemp.getCourseSearcher();
    }


}
