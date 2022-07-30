package org.example.coursecomparer.entities;

import org.example.coursegetter.entities.baseclasses.ScheduleEntry;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CourseTimeslots {
    private final int hours = 13;
    private final int days = 5;
    private final int timeOffset = 8;

    private final ScheduleEntry[][] courseTimeslots;

    public CourseTimeslots() {
        this.courseTimeslots = new ScheduleEntry[hours][days];
    }


    public ScheduleEntry[][] getCourseTimeslots() {
        return courseTimeslots;
    }

    public void addCourseTimeslots(ScheduleEntry courseSchedule) {
        DayOfWeek courseDay = courseSchedule.getDay();
        int day = courseDay.getValue();
        LocalTime courseStart = courseSchedule.getStartTime();
        LocalTime courseEnd = courseSchedule.getEndTime();
        int courseStartHour = courseStart.getHour() - timeOffset;
        int courseEndHour = courseEnd.getHour() - timeOffset;
        for (int i = courseStartHour; i < courseEndHour; i++) {
            courseTimeslots[i][day] = courseSchedule;
        }
    }

    public int getLength() {
        return courseTimeslots.length;
    }

    public ScheduleEntry[] getDay(int day) {
        return courseTimeslots[day];
    }


}
