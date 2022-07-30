package org.phase2.htmltables;


interface HTMLTableCell {
    /**
     * Note that TDs may be returned in this method, so any class
     * that uses an object of type HTMLTableCell should not
     * prematurely insert TDs around this method.
     *
     * @return HTML texts that would exist in a cell in an HTML table.
     */
    String getHTMLText();

    /**
     * If the table cell is used as a placeholder, returns true.
     *
     * @return check the predicate above.
     */
    boolean isEmpty();
}
