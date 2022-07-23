package org.phase2.studentrelated.entities;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a student.
 */
public class Student2 {

    /**
     * Items are course codes.
     * passedCourses and plannedCourses.keySet() do not have to be
     * disjoint, although a warning should pop up
     * if that is the case.
     */
    public Set<String> getPassedCourses() {
        return Collections.unmodifiableSet(this.passedCourses);
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
     *
     * Example:
     * {"CSC110Y1-F": {"LEC0101", "TUT0101"}, "MAT137Y1-Y": {"LEC0101": "TUT0101"}}
     */
    public Map<String, Set<String>> getPlannedCourses() {
        return Collections.unmodifiableMap(this.plannedCourses);
    }

    /**
     * Items are course codes.
     * passedCourses and plannedCourses.keySet() do not have to be
     * disjoint, although a warning should pop up
     * if that is the case.
     */
    private Set<String> passedCourses;

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
     *
     * Example:
     * {"CSC110Y1-F": {"LEC0101", "TUT0101"}, "MAT137Y1-Y": {"LEC0101": "TUT0101"}}
     */
    private Map<String, Set<String>> plannedCourses;

    public boolean addToPassedCourses(String crs) {
        return passedCourses.add(crs);
    }

    public boolean removeFromPassedCourses(String crs) {
        return passedCourses.remove(crs);
    }

    public boolean addToPlannedCourses(String crs) {
        if (plannedCourses.containsKey(crs)) {
            return false;
        }
        plannedCourses.put(crs, new TreeSet<>());
        return true;
    }

    /**
     * Adds a meeting to a planned course if it exists.
     * A planned course cannot have two lectures, so if there
     * are over two of them, the previous one will be removed.
     *
     * Once a meeting has been added, it cannot be removed, unless
     * you remove the course.
     *
     * @param crs     the course to target, e.g. CSC110Y1-F
     * @param meeting the meeting code, e.g. LEC0101 (must look like that)
     * @return whether a meeting was successfully added.
     * @throws RuntimeException if meeting does not look like a lec/tut/pra
     *                          code
     */
    public boolean addMeetingToPlannedCourse(String crs, String meeting) {
        if (!(meeting.matches("") && meeting.length() == 7)) return false;
        if (!plannedCourses.containsKey(crs)) return false;
        String firstThreeLetters = meeting.substring(0, 3);
        Set<String> meetings = plannedCourses.get(crs);
        meetings.removeIf(meetingCode -> meetingCode.startsWith(firstThreeLetters));
        meetings.add(meeting);
        return true;
    }


    public boolean removeFromPlannedCourses(String crs) {
        if (plannedCourses.containsKey(crs)) {
            plannedCourses.remove(crs);
            return true;
        } else return false;
    }


}
