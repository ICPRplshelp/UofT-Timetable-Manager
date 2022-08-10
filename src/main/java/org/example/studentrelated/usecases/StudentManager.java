package org.example.studentrelated.usecases;

import org.example.coursegetter.entities.baseclasses.Course;
import org.example.coursegetter.entities.baseclasses.Meetings;
import org.example.studentrelated.entities.Student2;
import org.example.studentrelated.presenters.IScheduleEntry;
import org.example.studentrelated.searchersusecases.UsableCourseSearcher;
import org.example.studentrelated.searchersusecases.UsableCourseSearcherPrev;

import java.util.*;

/**
 * I swear this isn't a singleton
 */
public class StudentManager {
    private final UsableCourseSearcher plannedSearcher;
    private final UsableCourseSearcherPrev pastSearcher;
    private final Student2 student;

    /**
     * Constructs this class.
     *
     * @param student         the student to manage
     * @param plannedSearcher a course searcher than can only obtain courses for the session 20229
     * @param pastSearcher    a course searcher than can obtain courses for the sessions 20199 - 20229, going for the
     *                        latest session possible
     */
    public StudentManager(Student2 student, UsableCourseSearcher plannedSearcher, UsableCourseSearcherPrev pastSearcher) {
        this.student = student;
        this.plannedSearcher = plannedSearcher;
        this.pastSearcher = pastSearcher;
    }

    /**
     * Adds crs to the passed courses if it exists.
     * The same passed course cannot be stored twice regardless of its session,
     * though you still need the session code.
     *
     * @param crs the course code, with or without the suffix -F/-Y/-S (will always be stored without the suffix)
     * @return whether adding the course was successful (was never offered before 20199 will cause it to fail).
     */
    public boolean addToPassedCourses(String crs) {

        // if the length of crs is over 8 characters long, reduce it to 8 characters
        if (crs.length() > 8) {
            crs = crs.substring(0, 8);
        }
        System.out.println(crs);
        Course course = pastSearcher.getCourse(crs);
        if (course == null) {
            return false;
        }
        return student.addToPassedCourses(crs);

    }

    /**
     * Adds crs to the planned courses if it exists.
     *
     * @param crs the course code, WITH the suffix -F/-S/-Y
     * @return whether adding the course was successful.
     */
    public boolean addToPlannedCourses(String crs) {
        Course course = plannedSearcher.getCourse(crs);
        if (course == null) {
            return false;
        }
        return student.addToPlannedCourses(crs);
    }

    /**
     * Removes crs from planned courses.
     *
     * @param crs the course code to remove
     * @return if the course was there and was removed.
     */
    public boolean removeFromPlannedCourses(String crs) {
        return student.removeFromPlannedCourses(crs);
    }

    /**
     * Removes crs from passed courses.
     *
     * @param crs the course code to remove
     * @return if the course was there and was removed.
     */
    public boolean removeFromPassedCourses(String crs) {
        return student.removeFromPassedCourses(crs);
    }

    /**
     * Adds a meeting to the planned course.
     * the course must actually support meetingCode,
     * or it will not be added.
     * <p>
     * Failure reasons include: meetingCode DNE,
     * or meeting DNE.
     *
     * @param course      the course to add to
     * @param meetingCode the meeting code
     * @return whether adding was successful
     */
    public boolean addMeetingToPlannedCourse(String course, String meetingCode) {
        Course crs = plannedSearcher.getCourse(course);
        if (crs == null) return false;
        Meetings meetings = crs.getMeetings();
        Set<String> allMeetings = new TreeSet<>();
        for (Set<String> strings : Arrays.asList(meetings.getLectures().keySet(),
                meetings.getTutorials().keySet(),
                meetings.getPracticals().keySet())) {
            allMeetings.addAll(strings);
        }
        if (allMeetings.contains(meetingCode)) {
            return student.addMeetingToPlannedCourse(course, meetingCode);
        } else return false;
    }


    /**
     * Items are course codes.
     * Course codes do not have the -F/-Y/-S suffix.
     * <p>
     * passedCourses and plannedCourses.keySet() do not have to be
     * disjoint, although a warning should pop up
     * if that is the case.
     */
    public Set<String> getPassedCourses() {
        return student.getPassedCourses();
    }


    /**
     * Keys are course codes (CSC110Y1-F);
     * values are selected lecture sections (LEC0101, TUT0101)
     * <p>
     * All values must start with
     * LEC, TUT, or PRA
     * and if so, they may only be used once and only
     * if a course requires it.
     * <p>
     * While it should be avoided at all costs, values may
     * have LEC/TUT/PRAs that are not in the course.
     * If that is the case, it should be treated by the program
     * as that specific meeting never existed.
     * <p>
     * Example:
     * {"CSC110Y1-F": {"LEC0101", "TUT0101"}, "MAT137Y1-Y": {"LEC0101": "TUT0101"}}
     */
    public Map<String, Set<String>> getPlannedCourses() {
        return student.getPlannedCourses();
    }

    /**
     * Grabs all the IScheduleEntry objects in Student, divided on session (F/S/Y)
     *
     * @return A map of 'F', 'S', 'Y' to a set of planned courses that belong
     * to that section.
     */
    public Map<Character, Set<IScheduleEntry>> getPlannedCourseSE() {
        Set<IScheduleEntry> fse = new HashSet<>();
        Set<IScheduleEntry> sse = new HashSet<>();
        Set<IScheduleEntry> yse = new HashSet<>();
        Map<Character, Set<IScheduleEntry>> toReturn = new HashMap<>();
        Map<String, Set<String>> plannedCourses = getPlannedCourses();
        constructPlannedCoursesSE(fse, sse, yse, plannedCourses);
        toReturn.put('F', setUnion(fse, yse));
        // toReturn.put('Y', Collections.unmodifiableSet(yse));
        toReturn.put('S', setUnion(sse, yse));
        return toReturn;
    }

    private Set<IScheduleEntry> setUnion(Set<IScheduleEntry> set1, Set<IScheduleEntry> set2){
        Set<IScheduleEntry> toReturn = new HashSet<>();
        toReturn.addAll(set1);
        toReturn.addAll(set2);
        return toReturn;
    }

    private void constructPlannedCoursesSE(Set<IScheduleEntry> fse, Set<IScheduleEntry> sse, Set<IScheduleEntry> yse, Map<String, Set<String>> plannedCourses) {
        for (String pc : plannedCourses.keySet()) {
            Set<String> lecs = plannedCourses.get(pc);
            for (String lec : lecs) {
                Set<IScheduleEntry> seTemp = plannedSearcher.getScheduleEntries(pc, lec);
                switch (pc.charAt(pc.length() - 1)) {
                    case 'F' -> fse.addAll(seTemp);
                    case 'S' -> sse.addAll(seTemp);
                    case 'Y' -> yse.addAll(seTemp);
                    default -> throw new IllegalStateException("Unexpected F/Y/S value: " + pc.charAt(pc.length() - 1));
                }
            }
        }
    }

    public UsableCourseSearcher getCSA() {
        return this.plannedSearcher;
    }

}
