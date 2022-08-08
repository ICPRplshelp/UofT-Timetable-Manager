package org.phase2.htmltables;

class ClockCell implements HTMLTableCell {

    private final int theHour;

    public ClockCell(int hourOfDay) {
        this.theHour = hourOfDay;
    }

    @Override
    public String getHTMLText() {
        return "<td>" + theHour + ":00</td>";
    }

    @Override
    public boolean isOccupied() {
        return true;
    }
}
