package org.phase2.htmltables;

/**
 * Used for cells that are occupied by a course before
 * because it did not last less than one hour long.
 */
public class OccupiedCell implements HTMLTableCell{
    @Override
    public String getHTMLText() {
        return "";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
