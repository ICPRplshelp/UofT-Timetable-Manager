package org.example.enrollmentissues.usecases;

import org.example.enrollmentissues.entities.*;
import org.example.enrollmentissues.entities.internal.CourseIssue;

public class IssueFactory {

    /**
     * Generates and returns a course issue.
     * @param problem the problem concerned.
     * @param course the course in question.
     * @return the course issue
     */
    public CourseIssue generateCourseIssue(Problem problem, String course){
        return switch(problem){
            case NO_PREREQUISITE -> new NoPrerequisite(course);
            case NO_COREQUISITE -> new NoCorequisite(course);
            case HAS_EXCLUSION -> new Exclusion(course);
            case FYF -> new FYF(course);
            default -> null;
        };
    }
}
