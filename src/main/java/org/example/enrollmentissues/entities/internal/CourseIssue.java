package org.example.enrollmentissues.entities.internal;


/**
 * A class representing an issue with a course.
 * Such issues cannot pinpoint specific lecture sections.
 */
public abstract class CourseIssue extends Issue {
    public CourseIssue(String course) {
        super(course);
    }
    // TODO: This class will likely be empty, but that is okay.
    // Classes that should extend this:
    // FYF issue
    // Prereq issue
    // Coreq issue
    // Excl issue
}
