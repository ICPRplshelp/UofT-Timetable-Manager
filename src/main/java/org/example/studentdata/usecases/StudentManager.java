package org.example.studentdata.usecases;

import org.example.coursegetter.entities.Course;
import org.example.studentdata.entities.CourseChoice;
import org.example.studentdata.entities.Student;

import java.util.List;

public class StudentManager {

    private final Student student;

    public StudentManager(Student student) {
        this.student = student;
    }

    /**
     * Adds a single planned course.
     *
     * @param courseChoice the course to add.
     * @return true.
     */
    public boolean addPlannedCourse(CourseChoice courseChoice) {
        student.addToPlannedCourses(List.of(courseChoice));
        return true;
    }

    /**
     * Adds a single blank course (with no meeting sections defined).
     *
     * @param course the course to add.
     * @return whether the course exists.
     */
    public boolean addBlankPlannedCourse(Course course) {
        if (course == null) {
            return false;
        }
        addPlannedCourse(new CourseChoice(course));
        return true;
    }

    /**
     * Removes a single planned course.
     *
     * @param courseChoice the course to remove.
     * @return whether the course exists.
     */
    public boolean removePlannedCourse(CourseChoice courseChoice) {
        if (courseChoice == null) {
            return false;
        }
        removePlannedCourses(List.of(courseChoice));
        return true;
    }

    /**
     * Sets a meeting section for a planned course.
     *
     * @param course  the course to set the meeting choice for.
     * @param section the section of the course.
     * @return whether the section prefix is correct.
     */
    public boolean setCourseChoice(CourseChoice course, String section) {
        String prefix = section.substring(0, Math.min(section.length(), 3));
        String number = section.substring(Math.min(section.length(), 3), Math.min(section.length(), 7));
        switch (prefix.toLowerCase()) {
            case "lec" -> course.setLectureSection("LEC" + number);
            case "tut" -> course.setTutSection("TUT" + number);
            case "pra" -> course.setPraSection("PRA" + number);
            default -> {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes a list of courses.
     *
     * @param courseList the list of courses to remove.
     * @return true.
     */
    public boolean removePlannedCourses(List<CourseChoice> courseList) {
        student.removeFromPlannedCourses(courseList);
        return true;
    }

    /**
     * Adds a single previous (taken) course.
     *
     * @param course the past course to add.
     * @return whether the course exists.
     */
    public boolean addPreviousCourse(Course course) {
        if (course == null) return false;
        student.addToPreviousCourses(List.of(course));
        return true;
    }

    /**
     * Removes a single previous course.
     *
     * @param course the past course to remove.
     * @return whether the course exists.
     */
    public boolean removePreviousCourse(Course course) {
        if (course == null) return false;
        student.removeFromPreviousCourses(List.of(course));
        return true;
    }

    /**
     * Returns the planned course if the student has added it to their planned courses.
     *
     * @param courseCode the string of the planned course.
     * @return the planned course.
     */
    public CourseChoice getPlannedCourseByString(String courseCode) {
        for (CourseChoice course : student.getPlannedCourses()) {
            if (courseCode.equals(course.getCourse().getOfferingCode())) {
                return course;
            }
        }
        return null;
    }

    /**
     * Returns the previous course if the student has added it to their previous courses.
     *
     * @param courseCode the string of the previous course.
     * @return the previous course.
     */
    public Course getPreviousCourseByString(String courseCode) {
        for (Course course : student.getAllPreviousCourses()) {
            if (courseCode.equals(course.getCode())) {
                return course;
            }
        }
        return null;
    }


}
