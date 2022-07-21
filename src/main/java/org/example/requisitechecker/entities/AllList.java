package org.example.requisitechecker.entities;

import java.util.Collection;
import java.util.Set;

public class AllList extends RequisiteList {
    private final Set<RequisiteList> requisiteListSet;

    public AllList(Set<RequisiteList> requisiteListSet) {
        this.requisiteListSet = requisiteListSet;
    }

    /**
     * If the AllList is empty, this method returns true.
     *
     * @param courses a set of courses to check.
     * @return whether courses fit the requisites.
     */
    @Override
    public boolean check(Collection<String> courses) {
        if (requisiteListSet.size() == 0)
            return true;
        // only one condition must fail for this entire
        // check to fail
        for (RequisiteList crs : requisiteListSet) {
            boolean state = !crs.check(courses);
            if (state)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return requisiteListSet.toString();
    }

    @Override
    public boolean alwaysTrue() {
        return requisiteListSet.size() == 0;
    }
}
