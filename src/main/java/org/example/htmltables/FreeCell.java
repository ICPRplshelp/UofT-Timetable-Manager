package org.example.htmltables;

class FreeCell implements HTMLTableCell {


    @Override
    public String getHTMLText() {
        return "<td>  </td>";
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
