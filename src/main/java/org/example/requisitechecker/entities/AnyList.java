package org.example.requisitechecker.entities;

import java.util.*;

public class AnyList extends TemplateList {
    private final Set<TemplateList> crses;

    public AnyList(Set<TemplateList> crses){
        this.crses = crses;
    }

    /**
     * If the AnyList is empty, this method still returns true.
     * @param courses a set of courses to check.
     * @return whether courses fit the requisites.
     */
    @Override
    public boolean check(Collection<String> courses) {
        if(crses.size() == 0)
            return true;
        for (TemplateList crs : crses){
            boolean state = crs.check(courses);
            if(state)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        var temp = crses.toString();
        return "(" + temp.substring(1, temp.length() - 1) + ")";
    }
}
