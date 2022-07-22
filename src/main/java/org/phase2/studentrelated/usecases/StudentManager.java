package org.phase2.studentrelated.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Meetings;
import org.example.coursegetter.entities.TeachingMethods;
import org.phase2.studentrelated.entities.Student2;

import java.util.*;

/**
 * I swear this isn't a singleton
 */
public class StudentManager {
    private final CrsSearcher plannedSearcher;
    private final CrsSearcher pastSearcher;
    private final Student2 student;

    /**
     * Constructs this class.
     * @param student the student to manage
     * @param plannedSearcher a course searcher than can only obtain courses for the session 20229
     * @param pastSearcher a course searcher than can obtain courses for the sessions 20199 - 20229, going for the
     *                     latest session possible
     */
    public StudentManager(Student2 student, CrsSearcher plannedSearcher, CrsSearcher pastSearcher) {
        this.student = student;
        this.plannedSearcher = plannedSearcher;
        this.pastSearcher = pastSearcher;
    }

    /**
     * Returns a list of courses the student has planned.
     *
     * @return a list of courses the student has planned.
     */
    public boolean addToPassedCourses(String crs) {
        Course course = pastSearcher.getCourse(crs);
        if (course == null) {
            return false;
        }
        return student.addToPassedCourses(crs);

    }

    /**
     * Adds crs to the planned courses if it exists; otherwise, cry about it.
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
        Meetings meetings = crs.getMeetings();
        Set<String> allMeetings = new TreeSet<>();
        for (Set<String> strings : Arrays.asList(meetings.getLectures().keySet(), meetings.getTutorials().keySet(), meetings.getPracticals().keySet())) {
            allMeetings.addAll(strings);
        }
        if (allMeetings.contains(meetingCode)) {
            return student.addMeetingToPlannedCourse(course, meetingCode);
        } else return false;
    }

    /**
     * Checks if the planned course: course is missing lec/tut/PRAs.
     *
     * @param course the course in question, which must be a planned course.
     * @return a set of teaching methods that were not added to the course.
     * null if there's a problem doing so.
     */
    public Set<TeachingMethods> checkMissingMeetings(String course) {
        Course crs = plannedSearcher.getCourse(course);
        if (crs == null) return null;
        Meetings met = crs.getMeetings();
        Set<String> meetingSet = student.getPlannedCourses().get(course);
        Set<TeachingMethods> existingMethods = buildExistingTeachingMethods(meetingSet);
        Set<TeachingMethods> requiredTMs = new HashSet<>();
        if (met.hasLectures() && !existingMethods.contains(TeachingMethods.LEC)) requiredTMs.add(TeachingMethods.LEC);
        if (met.hasTutorials() && !existingMethods.contains(TeachingMethods.TUT)) requiredTMs.add(TeachingMethods.TUT);
        if (met.hasPracticals() && !existingMethods.contains(TeachingMethods.PRA)) requiredTMs.add(TeachingMethods.PRA);
        return requiredTMs;
    }

    private Set<TeachingMethods> buildExistingTeachingMethods(Set<String> meetingSet) {
        Set<TeachingMethods> existingMethods = new HashSet<>();
        for (String meeting : meetingSet) {
            String firstThree = meeting.substring(0, 3);
            switch (firstThree) {
                case "LEC" -> existingMethods.add(TeachingMethods.LEC);
                case "TUT" -> existingMethods.add(TeachingMethods.TUT);
                case "PRA" -> existingMethods.add(TeachingMethods.PRA);
            }
        }
        return existingMethods;
    }

    public Set<String> getPassedCourses() {
        return student.getPassedCourses();
    }

    public Map<String, Set<String>> getPlannedCourses() {
        return student.getPlannedCourses();
    }


}
