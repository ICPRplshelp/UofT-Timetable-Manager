package org.example.coursegetter.entities;

import java.io.Serializable;
import java.util.*;

/**
 * This class lists the lectures, tutorials, and practicals for a single
 * course.
 * Iterating through each of them will always iterate them in
 * order of the lecture code.
 * Meaning LEC0101 < LEC0201 < LEC0301 < LEC0302 < LEC5101.
 * Note that MAT135H1-F and MAT135H1-S are considered
 * completely different courses for the purposes
 * of defining this class.
 */
public class Meetings  implements Serializable {
    // lectures
    // tutorials
    // practicals
    private final Map<String, Meeting> lectures = new TreeMap<>();
    private final Map<String, Meeting> tutorials = new TreeMap<>();
    private final Map<String, Meeting> practicals = new TreeMap<>();

    public Meetings(Map<String, Object> mInfo1, String crsCode) {
        for (Object hmv : mInfo1.values()) {
            Map<String, Object> hmv2 = (Map<String, Object>) hmv;
            Meeting tempMeeting = new Meeting(hmv2, crsCode);
            switch (tempMeeting.getTeachingMethod()) {
                case "LEC" -> lectures.put(tempMeeting.toString(), tempMeeting);
                case "TUT" -> tutorials.put(tempMeeting.toString(), tempMeeting);
                case "PRA" -> practicals.put(tempMeeting.toString(), tempMeeting);
            }
        }
    }

    /**
     * Iterating through this should always iterate
     * in sorted order.
     *
     * Always in the format AAA0000 (no dash in between)
     *
     * Meaning LEC0101, LEC0202, LEC0301, LEC0302, LEC5101
     * @return An unmodifiable view of the courses' lectures.
     */
    public Map<String, Meeting> getLectures() {
        return Collections.unmodifiableMap(lectures) ;
    }

    /**
     * Iterating through this should always iterate
     * in sorted order.
     * Meaning TUT0101, TUT0202, TUT0301, TUT0302, TUT5101
     * @return An unmodifiable view of the courses' tutorials.
     */
    public Map<String, Meeting> getTutorials() {
        return Collections.unmodifiableMap(tutorials);
    }

    /**
     * Iterating through this should always iterate
     * in sorted order.
     * Meaning PRA0101, PRA0202, PRA0301, PRA0302, PRA5101
     * @return An unmodifiable view of the courses' practicals.
     */
    public Map<String, Meeting> getPracticals() {
        return Collections.unmodifiableMap(practicals);
    }

    public boolean hasLectures() {
        return !lectures.isEmpty();
    }

    public boolean hasTutorials() {
        return !tutorials.isEmpty();
    }

    public boolean hasPracticals() {
        return !practicals.isEmpty();
    }

    public Collection<TeachingMethods> getTeachingMethods() {
        ArrayList<TeachingMethods> teachingMethods = new ArrayList<>();
        if (hasLectures()) teachingMethods.add(TeachingMethods.LEC);
        if (hasTutorials()) teachingMethods.add(TeachingMethods.TUT);
        if (hasPracticals()) teachingMethods.add(TeachingMethods.PRA);
        return teachingMethods;
    }
}
