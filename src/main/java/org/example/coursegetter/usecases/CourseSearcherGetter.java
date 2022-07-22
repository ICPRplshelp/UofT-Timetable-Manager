package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.SessionStorage;

/**
 * Get the course searcher WITHOUT violating clean architecture!!!!
 */
public class CourseSearcherGetter {

    public CourseSearcherIndividual getCourseSearcher() {
        return csr;
    }

    private final CourseSearcherIndividual csr;

    public CourseSearcherGetter() {
        CourseStorageObtainer clt = new CourseStorageObtainer();
        SessionStorage cls = clt.obtainAllCourses();
        csr = new CourseSearcherIndividual(cls);
    }
}
