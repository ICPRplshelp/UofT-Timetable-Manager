package org.phase2.htmltables;

/**
 * Used for cells that are occupied by a course before
 * because it did not last less than one hour long.
 */
class OccupiedCell implements HTMLTableCell {
    @Override
    public String getHTMLText() {
        return "";
    }

    @Override
    public boolean isOccupied() {
        return true;
    }
}
