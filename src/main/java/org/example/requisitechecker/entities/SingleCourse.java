package org.example.requisitechecker.entities;

import java.util.Collection;

public class SingleCourse extends TemplateList {

    private final String crs;

    /**
     * Constructs this class.
     *
     * @param crs a course like CSC110Y1.
     */
    public SingleCourse(String crs) {
        this.crs = crs;
    }

    @Override
    public boolean check(Collection<String> courses) {
        return courses.contains(crs);
    }


    @Override
    public String toString() {
        return this.crs;
    }
}
