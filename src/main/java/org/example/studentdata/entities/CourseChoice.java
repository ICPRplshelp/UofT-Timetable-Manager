package org.example.studentdata.entities;

import org.example.coursegetter.entities.Course;

public class CourseChoice implements Comparable<CourseChoice> {
    public String getLectureSection() {
        return lectureSection;
    }

    public void setLectureSection(String lectureSection) {
        this.lectureSection = lectureSection;
    }

    public String getTutSection() {
        return tutSection;
    }

    public void setTutSection(String tutSection) {
        this.tutSection = tutSection;
    }

    public String getPraSection() {
        return praSection;
    }

    public void setPraSection(String praSection) {
        this.praSection = praSection;
    }


    public Course getCourse() {
        return course;
    }

    private final Course course;
    private String lectureSection;
    private String tutSection;
    private String praSection;

    public CourseChoice(Course course, String lectureSection, String tutSection,
                        String praSection) {
        this.course = course;
        this.lectureSection = lectureSection;
        this.tutSection = tutSection;
        this.praSection = praSection;

        // TODO: Enforce correct choices in a use case class
    }

    public CourseChoice(Course course, String lectureSection, String tutSection){
        this(course, lectureSection, tutSection, null);
    }

    @Override
    public int compareTo(CourseChoice o) {
        return course.compareTo(o.course);
    }

    /**
     * @return false if and only if the lecture session for this choice is missing.
     */
    public boolean checkLec(){
        return !this.course.getMeetings().hasLectures() || lectureSection != null;
    }

    /**
     * @return false if and only if the tutorial session for this choice is missing.
     */
    public boolean checkTut(){
        return !this.course.getMeetings().hasLectures() || tutSection != null;
    }

    /**
     * @return false if and only if the practical session for this choice is missing.
     */
    public boolean checkPra(){
        return !this.course.getMeetings().hasLectures() || praSection != null;
    }

}
