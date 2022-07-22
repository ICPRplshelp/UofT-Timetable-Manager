package org.phase2.warningrelated.requisitechecker.entities;

import java.util.Collection;

/**
 * This is a recursive list data structure that concretely represents
 * the prerequisite string one would see in a UofT course list.
 */
public abstract class RequisiteList {

    /**
     * Given a collection of courses, this method
     * checks if the collection of courses meets
     * the requirements of this RequisiteList.
     *
     * @param courses a set of courses to check.
     * @return the result
     */
    public abstract boolean check(Collection<String> courses);

    public boolean alwaysTrue() {
        return false;
    }
}
