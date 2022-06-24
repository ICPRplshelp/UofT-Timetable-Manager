package org.example.requisitechecker.entities;

import java.util.Collection;

/**
 * This always returns true when checked.
 */
public class FreePass extends TemplateList {
    @Override
    public boolean check(Collection<String> courses) {
        return true;
    }

    @Override
    public String toString(){
        return "-";
    }

    @Override
    public boolean alwaysTrue() {return true;}
}
