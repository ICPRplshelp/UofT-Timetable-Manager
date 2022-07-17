package org.example.enrollmentissues.entities;

import org.example.coursegetter.entities.ScheduleEntry;

public record ScheduleEntryIdentifier(String course,
                                      String meeting,
                                      String ScheduleEntry) {}
