package org.example.enrollmentissues.entities;

public abstract class Issue {
    public final Problem getProblem() {
        return problem;
    }

    public final WarningLevel getWarningLevel() {
        return warningLevel;
    }

    public final String getCourse() {
        return course;
    }
    protected Problem problem;  // to be set.
    protected WarningLevel warningLevel;  // to be set.
    protected String course;
    public Issue(String course){
        this.course = course;
    }
}
