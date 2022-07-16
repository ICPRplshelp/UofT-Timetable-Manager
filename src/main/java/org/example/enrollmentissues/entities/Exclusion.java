package org.example.enrollmentissues.entities;

import org.example.enrollmentissues.entities.internal.CourseIssue;

public class Exclusion extends CourseIssue {

    public Exclusion(String course) {
        super(course);
        this.problem = Problem.HAS_EXCLUSION;
    }
}
