package org.example.enrollmentissues.entities;

import org.example.enrollmentissues.entities.internal.CourseIssue;

public class NoCorequisite extends CourseIssue {

    public NoCorequisite(String course) {
        super(course);
        this.problem = Problem.NO_COREQUISITE;
    }
}
