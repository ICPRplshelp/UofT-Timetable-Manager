package org.example.enrollmentissues.entities;

import org.example.enrollmentissues.entities.internal.CourseIssue;

public class FYF extends CourseIssue {

    public FYF(String course) {
        super(course);
        this.problem = Problem.FYF;
    }
}
