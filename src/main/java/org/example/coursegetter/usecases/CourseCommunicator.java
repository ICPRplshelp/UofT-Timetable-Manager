package org.example.coursegetter.usecases;

import org.example.coursegetter.entities.baseclasses.Course;

import java.util.Collection;

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


}
