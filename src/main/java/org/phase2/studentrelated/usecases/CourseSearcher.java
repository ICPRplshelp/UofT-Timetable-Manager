package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.SessionStorage;
import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meetings;
import org.example.coursegetter.entities.baseclasses.ScheduleEntry;
import org.example.coursegetter.usecases.CourseInputValidator;
import org.example.coursegetter.usecases.CourseStorageObtainer;
import org.phase2.studentrelated.presenters.IScheduleEntry;

import java.util.*;

/**
 * This class may be used to:
 * - Return all searchable code
 * - Get information about one course
 * <p>
 * This is the one that only tries to look for 20229 courses
 */
public class CourseSearcher {
    private final String curSession = "20229";
    private final SessionStorage sessionStorage;
    private final CourseInputValidator courseInputValidator = new CourseInputValidator();


    public CourseSearcher() {
        CourseStorageObtainer clt = new CourseStorageObtainer();
        sessionStorage = clt.obtainAllCourses();
    }

    public CourseSearcher(SessionStorage ss) {
        sessionStorage = ss;
    }

    /**
     * Gets the Course object from code.
     *
     * @param code e.g. CSC110Y1-F (must include -F/-S/-Y suffix)
     * @return the course if it exists, or null otherwise.
     */
    public Course getCourse(String code) {
        return getCourseOfferingByCode(curSession, code);
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
        return getAllCoursesOfferingList(curSession);
    }


    /**
     * Gets a course.
     *
     * @param crsCode the course code, in a format similar to CSC110Y1-F
     * @return the Course if one is found, or null otherwise.
     */
    public Course getCourseOfferingByCode(String session, String crsCode) {
        String searchableCourse = courseInputValidator.courseOfferingToSearchableCourse(crsCode);
        if (searchableCourse == null) return null;
        return sessionStorage.getSession(session).getCourse(searchableCourse);
    }

    /**
     * Given a course code, this method searches for all course
     * offerings by this course code.
     *
     * @param crsCode the course code such as MAT135H1
     * @return a collection of courses such as MAT135H1-F, MAT135H1-S, MAT135H1-Y
     */
    public Collection<Course> getCourseByCourseCode(String session, String crsCode) {
        List<Course> courseList = new ArrayList<>();
        String[] suffixes = {"-F", "-S", "-Y"};
        for (String suffix : suffixes) {
            String courseToSearch = crsCode + suffix;
            Course tempCourse = getCourseOfferingByCode(session, courseToSearch);
            if (tempCourse != null)
                courseList.add(tempCourse);
        }
        return courseList;
    }

    /**
     * Returns a set of all courses that can be reached from the given course
     * storage.
     * The set may not be modified.
     *
     * @return a set of all courses that can be reached from the given course storage.
     */
    public Set<String> getAllCoursesOfferingList(String session) {
        return sessionStorage.getSession(session).getCourseOfferingListAsString();
    }
}
