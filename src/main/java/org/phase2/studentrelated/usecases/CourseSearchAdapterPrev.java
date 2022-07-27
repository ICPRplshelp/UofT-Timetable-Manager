package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Capable of searching courses from previous sections.
 * Terrible news for the user.
 * <p>
 * By the way, I'm not responsible for what is returned by
 * getScheduleEntries or getMeetings.
 * This may be a refused bequest, or it might not be one.
 */
public class CourseSearchAdapterPrev {

    private final String[] sessions = {"20229", "20225", "20219", "20215", "20209", "20205", "20199"};
    private final CourseSearcherIndividual courseSearcher;

    public CourseSearchAdapterPrev() {
        CourseSearcherGetter csg = new CourseSearcherGetter();
        this.courseSearcher = csg.getCourseSearcher();
    }

    private CourseSearcherIndividual getCourseSearcher() {
        return courseSearcher;
    }

    /**
     * Obtain course information from course code.
     * @param code the course code, with or without the suffix (don't use the suffix for best results)
     * @return the course information, although all we really need to
     * do with this is to verify that this was a course that was
     * taken in the past.
     */
    public Course getCourse(String code) {
        if (code.length() > 7) return getCourseOffering(code);
        else {
            return getCoursePlain(code);
        }
    }

    /**
     * Try its best to grab course info for a course code with
     * the suffix -F/-S/-Y.
     * @param code a course code with the suffix -F/-S/-Y
     * @return the course, if possible.
     * Please do not check its
     * lecture sections - the metadata for it is enough.
     */
    @Nullable
    private Course getCourseOffering(String code) {
        for (String ses : sessions) {
            Course obtainedCourse = getCourseSearcher().getCourseOfferingByCode(ses, code);
            if (!Objects.isNull(obtainedCourse)) {
                return obtainedCourse;
            }
        }

        return null;
    }

    /**
     * Try its best to grab course info for a course code WITHOUT
     * the suffix -F/-S/-Y.
     * @param code a course code WITHOUT the suffix -F/-S/-Y
     * @return the course, if possible.
     * Please do not check its
     * lecture sections - the metadata for it is enough.
     */
    @Nullable
    private Course getCoursePlain(String code) {
        for (String ses : sessions) {
            Collection<Course> obtainedCourses = getCourseSearcher().getCourseByCourseCode(ses, code);
            if (obtainedCourses.size() != 0) {
                for (Course obtainedCourse : obtainedCourses) {
                    return obtainedCourse;
                }
            }
        }
        return null;
    }
}
