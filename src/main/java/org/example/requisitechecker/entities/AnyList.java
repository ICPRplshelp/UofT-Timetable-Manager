package org.example.requisitechecker.entities;

import java.util.Collection;
import java.util.Set;

public class AnyList extends RequisiteList {
    private final Set<RequisiteList> requisiteListSet;

    public AnyList(Set<RequisiteList> requisiteListSet) {
        this.requisiteListSet = requisiteListSet;
    }

    /**
     * If the AnyList is empty, this method still returns true.
     *
     * @param courses a set of courses to check.
     * @return whether courses fit the requisites.
     */
    @Override
    public boolean check(Collection<String> courses) {
        if (requisiteListSet.size() == 0)
            return true;
        for (RequisiteList crs : requisiteListSet) {
            boolean state = crs.check(courses);
            if (state)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        var temp = requisiteListSet.toString();
        return "(" + temp.substring(1, temp.length() - 1) + ")";
    }

    @Override
    public boolean alwaysTrue() {
        return requisiteListSet.size() == 0;
    }

}
