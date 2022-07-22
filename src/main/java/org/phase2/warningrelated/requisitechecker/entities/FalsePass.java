package org.phase2.warningrelated.requisitechecker.entities;

import java.util.Collection;

/**
 * This always returns false when checked.
 */
public class FalsePass extends RequisiteList {

    @Override
    public boolean check(Collection<String> courses) {
        return false;
    }

    @Override
    public String toString() {
        return "-";
    }

}
