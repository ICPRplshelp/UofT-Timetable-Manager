package org.example.requisitechecker.entities;

import java.util.Collection;
import java.util.Set;

public class AllList extends TemplateList {
    private final Set<TemplateList> crses;

    public AllList(Set<TemplateList> crses){
        this.crses = crses;
    }

    /**
     * If the AllList is empty, this method returns true.
     * @param courses a set of courses to check.
     * @return whether courses fit the requisites.
     */
    @Override
    public boolean check(Collection<String> courses) {
        if(crses.size() == 0)
            return true;
        // only one condition mujst fail for this entire
        // check to fail
        for (TemplateList crs : crses){
            boolean state = !crs.check(courses);
            if(state)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return crses.toString();
    }

    @Override
    public boolean alwaysTrue() {
        return crses.size() == 0;
    }
}
