package org.example.enrollmentissues.entities;

import org.example.enrollmentissues.entities.internal.CourseIssue;

public class NoPrerequisite extends CourseIssue {

    public NoPrerequisite(String course) {
        super(course);
        this.problem = Problem.NO_PREREQUISITE;
    }
}
