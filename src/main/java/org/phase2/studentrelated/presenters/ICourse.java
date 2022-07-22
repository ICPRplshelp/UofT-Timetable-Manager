package org.phase2.studentrelated.presenters;

/**
 * Assume these methods are null safe.
 * Anything implementing this may be used by a presenter
 * given it is typed ICourse.
 * For example,
 * ICourse crsInfo = getCourseInfo("CSC110Y1-F");
 * is permitted in a presenter.
 */
public interface ICourse {
    String getCourseTitle();
    String getCourseDescription();
    String getBreadthCategories();
    String getPrerequisite();
    String getExclusion();
    String getCorequisite();
}
