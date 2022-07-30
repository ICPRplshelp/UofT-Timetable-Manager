package org.phase2.htmltables;

class HeaderCell implements HTMLTableCell {

    private final String headText;

    public HeaderCell(int id) {
        headText = switch (id) {
            case 0 -> "";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    @Override
    public String getHTMLText() {
        return "<td>" + headText + "</td>";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
