package org.example.htmltables;

/**
 * This is in the presenter layer as it decides what to place there.
 * This generates HTML code for HTML tables.
 */
class TableGenerator {

    /**
     * Generates the HTML inner table.
     * This does not return the table
     * tags, but everything that would be placed inside, including
     * thead and tbody.
     *
     * @param thead an array of strings that would be placed inside thead.
     *              blank if no head needed.
     * @param tbody a 2D array of strings that would be placed inside tbody.
     * @return the generated inner table
     */
    public String generateInnerHTMLTable(HTMLTableCell[] thead, HTMLTableCell[][] tbody) {
        // table head part
        StringBuilder sb = new StringBuilder();
        if (thead.length != 0) {
            generateTableHead(thead, sb);
        }
        // table body part
        if (tbody.length != 0) {
            generateTableBody(tbody, sb);
        }

        return sb.toString();
    }

    private void generateTableBody(HTMLTableCell[][] tbody, StringBuilder sb) {
        sb.append("<tbody>");
        for (HTMLTableCell[] tbodyRow : tbody) {
            sb.append("<tr>");
            for (HTMLTableCell tCell : tbodyRow) {
                sb.append(tCell.getHTMLText());
            }
            sb.append("</tr>");
        }
        sb.append("</tbody>");
    }

    private void generateTableHead(HTMLTableCell[] thead, StringBuilder sb) {
        sb.append("<thead><tr>");
        int i = 0;
        for (HTMLTableCell thCell : thead) {
            // TODO: Likely something that forbids anything before 8AM...
            sb.append(thCell.getHTMLText());
            i++;
        }
        sb.append("</tr></thead>");
    }
}
