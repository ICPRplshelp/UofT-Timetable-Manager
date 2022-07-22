package org.phase2.warningrelated.requisitechecker.entities;

import java.util.Collection;
import java.util.Set;

public class AnyList extends RequisiteList {
    private final Set<RequisiteList> crses;

    public AnyList(Set<RequisiteList> crses) {
        this.crses = crses;
    }

    /**
     * If the AnyList is empty, this method still returns true.
     *
     * @param courses a set of courses to check.
     * @return whether courses fit the requisites.
     */
    @Override
    public boolean check(Collection<String> courses) {
        if (crses.size() == 0)
            return true;
        for (RequisiteList crs : crses) {
            boolean state = crs.check(courses);
            if (state)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        var temp = crses.toString();
        return "(" + temp.substring(1, temp.length() - 1) + ")";
    }

    @Override
    public boolean alwaysTrue() {
        return crses.size() == 0;
    }

}
