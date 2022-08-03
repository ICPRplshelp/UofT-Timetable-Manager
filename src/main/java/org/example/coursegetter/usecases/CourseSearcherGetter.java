package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.SessionStorage;
import org.phase2.studentrelated.usecases.CourseSearcher;

/**
 * Get the course searcher WITHOUT violating clean architecture!!!!
 */
public class CourseSearcherGetter {

    public CourseSearcher getCourseSearcher() {
        return csa;
    }

    private final CourseSearcher csa;

    public CourseSearcherGetter() {
        CourseStorageObtainer clt = new CourseStorageObtainer();
        SessionStorage cls = clt.obtainAllCourses();
        csa = new CourseSearcher(cls);
    }
}
