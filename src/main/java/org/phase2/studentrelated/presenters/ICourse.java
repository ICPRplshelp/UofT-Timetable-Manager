package org.phase2.studentrelated.presenters;

import org.example.coursegetter.entities.Meetings;

/**
 * Assume these methods are null safe.
 * Anything implementing this may be used by a presenter
 * given it is typed ICourse.
 * For example,
 * ICourse crsInfo = getCourseInfo("CSC110Y1-F");
 * is permitted in a presenter.
 */
public interface ICourse {
    double getCreditValue();
    String getCode();
    String getOfferingCode();
    String getPrerequisite();
    String getExclusion();
    String getSection();
    String getCourseDescription();
    String getBreadthCategories();
    String getDeliveryInstructions();
    String getCourseTitle();
    String getCorequisite();
    Meetings getMeetings();
}
