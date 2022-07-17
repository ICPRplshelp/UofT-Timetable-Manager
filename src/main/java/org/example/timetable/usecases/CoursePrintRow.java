package org.example.timetable.usecases;

import java.util.Collection;

/**
 * This record acts as a way to return multiple
 * values of different types.
 * This means that this class may be accessed
 * by the presenter, even if otherwise restricted.
 *
 * PLEASE GIVE ME A WAY TO FIX THIS -
 * COMMUNICATING TO THE PRESENTER IS SO HARD
 * WHEN YOU HAVE NESTED DATA STRUCTURES
 */
public record CoursePrintRow(String courseCode, Collection<String>
                             lectureSessions) {
}
