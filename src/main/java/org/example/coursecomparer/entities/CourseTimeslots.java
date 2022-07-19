package org.example.coursecomparer.entities;
import org.example.coursegetter.entities.ScheduleEntry;
import java.time.DayOfWeek;
import java.time.LocalTime;

import java.util.List;

public class CourseTimeslots {
    private final ScheduleEntry[][] courseTimeslots;

    public CourseTimeslots(){
        this.courseTimeslots = new ScheduleEntry[12][5];
    }


    public ScheduleEntry[][] getCourseTimeslots() {
        return courseTimeslots;
    }

    public void addCourseTimeslots(ScheduleEntry courseSchedule) {
        DayOfWeek courseDay = courseSchedule.getDay();
        int day = courseDay.getValue();
        LocalTime courseStart = courseSchedule.getStartTime();
        LocalTime courseEnd = courseSchedule.getEndTime();
        int courseStartHour = courseStart.getHour() - 9;
        int courseEndHour = courseEnd.getHour() - 9;
        for (int i = courseStartHour; i < courseEndHour; i++) {
            courseTimeslots[i][day] = courseSchedule;
        }
    }

    public int getLength(){
        return courseTimeslots.length;
    }

    public ScheduleEntry[] getDay(int day){
        return courseTimeslots[day];
    }


}
