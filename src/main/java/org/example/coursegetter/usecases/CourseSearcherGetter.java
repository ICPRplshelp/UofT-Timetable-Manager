package org.example.coursegetter.usecases;

import org.example.coursegetter.usecases.internal.CourseListObtainer;
import org.example.coursegetter.usecases.internal.CourseSearcher;

/**
 * Get the course searcher WITHOUT violating clean architecture!!!!
 */
public class CourseSearcherGetter {
    private final CourseListObtainer clt;
    public final CourseSearcher csr;

    public CourseSearcherGetter(){
        clt = new CourseListObtainer();
        var cls = clt.obtainAllCourses();
        csr = new CourseSearcher(cls);
    }
}
