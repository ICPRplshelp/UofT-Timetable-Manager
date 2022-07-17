package org.example.enrollmentissues.entities;

public enum Problem {
    CONFLICT,
    TO_FAR,
    FROM_FAR,
    NO_PREREQUISITE,
    NO_COREQUISITE,
    NO_RECOMMENDED_PREPARATION,  // very weak warning.
    HAS_EXCLUSION,
    FYF
}
