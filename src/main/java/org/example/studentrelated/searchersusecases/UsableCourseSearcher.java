package org.example.studentrelated.searchersusecases;

import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meetings;
import org.example.coursegetter.entities.baseclasses.ScheduleEntry;
import org.example.coursegetter.usecases.CourseSearcherGetter;
import org.example.coursegetter.usecases.CourseSearcherIndividual;
import org.example.studentrelated.presenters.IScheduleEntry;

import java.util.*;

/**
 * I didn't want to rewrite the entire course
 * searching thing, so I've created this to get around it
 * <p>
 * This is the one that only tries to look for 20229 courses
 */
public class UsableCourseSearcher {
    private final String curSession = "20229";

    private static UsableCourseSearcher csa;

    private UsableCourseSearcher() {
        CourseSearcherGetter csg = new CourseSearcherGetter();
        this.courseSearcher = csg.getCourseSearcher();
    }

    public static UsableCourseSearcher getInstance() {
        if (csa == null) {
            csa = new UsableCourseSearcher();
        }
        return csa;
    }

    public CourseSearcherIndividual getCourseSearcher() {
        return courseSearcher;
    }

    private final CourseSearcherIndividual courseSearcher;

    /**
     * Gets the Course object from code.
     *
     * @param code e.g. CSC110Y1-F (must include -F/-S/-Y suffix)
     * @return the course if it exists, or null otherwise.
     */
    public Course getCourse(String code) {
        return getCourseSearcher().getCourseOfferingByCode(curSession, code);
    }

    /**
     * Get the course meetings from the code.
     *
     * @param code e.g. CSC110Y1-F (must include -F/-S/-Y suffix)
     * @return the set of meetings if it exists, or null otherwise.
     */
    public Set<String> getMeetings(String code) {
        Course crs = getCourse(code);
        if (Objects.isNull(crs)) {
            return new HashSet<>();
        }
        Meetings meetings = crs.getMeetings();
        Set<String> lecs = meetings.getLectures().keySet();
        Set<String> tuts = meetings.getTutorials().keySet();
        Set<String> pras = meetings.getPracticals().keySet();
        // combine the above three sets into a list, in the order of top to bottom
        Set<String> all = new TreeSet<>();
        all.addAll(lecs);
        all.addAll(tuts);
        all.addAll(pras);
        return all;
    }

    /**
     * Returns all schedule entries from the course.
     * Do NOT include async sections.
     *
     * @param code    the course code
     * @param meeting the meeting
     * @return the schedule entries, if applicable
     */
    public Set<IScheduleEntry> getScheduleEntries(String code, String meeting) {
        Course crs = getCourse(code);
        if (Objects.isNull(crs)) {
            return new HashSet<>();
        }
        Meetings meetings = crs.getMeetings();
        for (var ltp : List.of(meetings.getLectures(), meetings.getTutorials(), meetings.getPracticals())) {
            if (ltp.containsKey(meeting)) {
                Set<ScheduleEntry> ise = ltp.get(meeting).getScheduleEntries();
                return Collections.unmodifiableSet(ise);
            }
        }
        return new HashSet<>();
    }

    /**
     * Returns a set of all courses that can be reached from the given course storage. The set may not be modified.
     *
     * @return check desc
     */
    public Set<String> getAllCourses() {
        return getCourseSearcher().getAllCoursesOfferingList("20229");
    }
}
