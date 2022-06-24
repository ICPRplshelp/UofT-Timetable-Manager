package org.example.coursegetter;

import org.example.coursegetter.usecases.internal.CourseSearcher;
import org.example.coursegetter.usecases.CourseSearcherGetter;

/**
 * Create a course searcher. Now you can use it whenever you want.
 */
public class CourseMiniController {

    // feel free to do what you want with this thing
    private final CourseSearcher courseSearcher;


    public CourseMiniController() {
        var temp = new CourseSearcherGetter();
        courseSearcher = temp.csr;
    }


}
