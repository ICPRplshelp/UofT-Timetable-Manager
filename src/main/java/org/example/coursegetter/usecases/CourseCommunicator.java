package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.Course;
import org.example.coursegetter.entities.Meeting;
import org.example.coursegetter.entities.ScheduleEntry;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A use case class to pass course entity info to the controller
 * and the presenter, without violating clean architecture.
 */
public class CourseCommunicator {
    private final Course course;

    public CourseCommunicator(Course course) {
        this.course = course;
    }

    public String getCourseTitle() {
        return this.course.getCourseTitle();
    }

    public String getCourseDescription() {
        return this.course.getCourseDescription();
    }

    public String getPrerequisite() {
        return this.course.getPrerequisite();
    }

    public String getExclusion() {
        return this.course.getExclusion();
    }

    public String getBreadthCategories() {
        return this.course.getBreadthCategories();
    }

    public String getDeliveryInstructions() {
        return this.course.getDeliveryInstructions();
    }

    public Collection<String> getLectures() {
        return this.course.getMeetings().getLectures().keySet();
    }

    public Collection<String> getTutorials() {
        return this.course.getMeetings().getTutorials().keySet();
    }

    public Collection<String> getPracticals() {
        return this.course.getMeetings().getPracticals().keySet();
    }

    /**
     * Get the days the lecture code of this course is offered in.
     *
     * @param lectureCode the lecture code, LEC0101
     * @return a list of days this lecture is offered in (empty if this is async).
     */
    public Collection<DayOfWeek> getDays(String lectureCode) {
        Map<String, Meeting> lecturesMap = decideLECTUTPRA(lectureCode);
        if (lecturesMap == null) return new HashSet<>();
        Set<ScheduleEntry> scheduleEntries = lecturesMap.get(lectureCode).getScheduleEntries();
        Set<DayOfWeek> daysSoFar = new HashSet<>();
        scheduleEntries.forEach(se -> {
            if (se.getDay() != null) {
                daysSoFar.add(se.getDay());
            }
        });
        return daysSoFar;
    }


    /**
     * Return the end time of the course's lecture code based on the day of week
     * it is offered.
     * If we can't find a time, return MIDNIGHT.
     *
     * @param lectureCode e.g. LEC0101
     * @param dayOfWeek   e.g. MO, WE, TU, ...
     * @return the start time of the course's lecture code based on the day of week
     * it is offered.
     */
    public LocalTime getEndTime(String lectureCode, DayOfWeek dayOfWeek) {
        return getLocalTimeHelper(lectureCode, dayOfWeek, true);
    }

    private LocalTime getLocalTimeHelper(String lectureCode, DayOfWeek dayOfWeek, boolean endInsteadOfStart) {
        Map<String, Meeting> lecturesMap = decideLECTUTPRA(lectureCode);
        if (lecturesMap == null) return LocalTime.MIDNIGHT;
        Set<ScheduleEntry> scheduleEntries = lecturesMap.get(lectureCode).getScheduleEntries();
        return !endInsteadOfStart ? scheduleEntries.stream().filter(scheduleEntry -> scheduleEntry.getDay().equals(dayOfWeek)).findFirst().map(ScheduleEntry::getStartTime).orElse(LocalTime.MIDNIGHT) : scheduleEntries.stream().filter(scheduleEntry -> scheduleEntry.getDay().equals(dayOfWeek)).findFirst().map(ScheduleEntry::getEndTime).orElse(LocalTime.MIDNIGHT);
    }


    private Map<String, Meeting> decideLECTUTPRA(String lectureCode) {
        String lecId = lectureCode.substring(0, 3);
        return switch (lecId) {
            case "LEC" -> this.course.getMeetings().getLectures();
            case "TUT" -> this.course.getMeetings().getTutorials();
            case "PRA" -> this.course.getMeetings().getPracticals();
            default -> null;
        };
    }


}
