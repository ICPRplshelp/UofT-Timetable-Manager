package org.example.requisitechecker.entities;

import java.util.Collection;

/**
 * This always returns false when checked.
 */
public class FalsePass extends TemplateList {

    @Override
    public boolean check(Collection<String> courses) {
        return false;
    }

    @Override
    public String toString() {
        return "-";
    }

}
