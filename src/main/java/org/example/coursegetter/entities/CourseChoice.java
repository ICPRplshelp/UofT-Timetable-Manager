package org.example.coursegetter.entities;

public class CourseChoice implements Comparable<CourseChoice> {
    public final Course course;
    public final String lectureSection;
    public final String tutSection;

    public CourseChoice(Course course, String lectureSection, String tutSection) {
        this.course = course;
        this.lectureSection = lectureSection;
        this.tutSection = tutSection;
    }

    @Override
    public int compareTo(CourseChoice o) {
        return course.compareTo(o.course);
    }
}
