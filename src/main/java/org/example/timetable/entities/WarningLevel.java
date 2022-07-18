package org.example.timetable.entities;

public enum WarningLevel {
    WEAK_INFO,  // why do we have this?
    INFO,  // you can take this course/lecture, but it's a bit inconvenient to
    WARNING,  // you can take this course or this lecture, but
    SEVERE,  // you can't take this course without a waiver.
    CRITICAL  // you really can't take this course.
}
