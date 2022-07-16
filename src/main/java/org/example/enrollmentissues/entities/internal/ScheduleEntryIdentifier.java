package org.example.enrollmentissues.entities.internal;

import org.example.coursegetter.entities.ScheduleEntry;

public record ScheduleEntryIdentifier(String course,
                                      String meeting,
                                      String ScheduleEntry) {}
