package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.CourseStorage;
import org.example.coursegetter.usecases.internal.CourseListObtainer;
import org.example.coursegetter.usecases.internal.CourseSearcherIndividual;

/**
 * Get the course searcher WITHOUT violating clean architecture!!!!
 */
public class CourseSearcherGetter {

    public CourseSearcherIndividual getCourseSearcher() {
        return csr;
    }

    private final CourseSearcherIndividual csr;

    public CourseSearcherGetter(){
        CourseListObtainer clt = new CourseListObtainer();
        CourseStorage cls = clt.obtainAllCourses();
        csr = new CourseSearcherIndividual(cls);
    }
}
