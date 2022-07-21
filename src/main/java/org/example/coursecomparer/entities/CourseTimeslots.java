package org.example.coursecomparer.entities;
import org.example.coursegetter.entities.ScheduleEntry;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class CourseTimeslots {
    private static final int HOURS = 13;
    private static final int DAYS = 5;
    private static final int TIMEOFFSET = 8;

    private final ScheduleEntry[][] courseTimeslots;

    public CourseTimeslots(){
        this.courseTimeslots = new ScheduleEntry[HOURS][DAYS];
    }


    public ScheduleEntry[][] getCourseTimeslots() {
        return courseTimeslots;
    }

    public void addCourseTimeslots(ScheduleEntry courseSchedule) {
        DayOfWeek courseDay = courseSchedule.getDay();
        int day = courseDay.getValue();
        LocalTime courseStart = courseSchedule.getStartTime();
        LocalTime courseEnd = courseSchedule.getEndTime();
        int courseStartHour = courseStart.getHour() - TIMEOFFSET;
        int courseEndHour = courseEnd.getHour() - TIMEOFFSET;
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
