package org.phase2.htmlutilities;

import java.util.Collection;

public class HTMLListGen {

    /**
     * Generates an ordered HTML list out of items.
     * @param items the items to pass in.
     * @return the HTML list.
     */
    public String generateHTMLList(Collection<String> items){
        // create an HTML list out of items
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        for (String item : items) {
            sb.append("<li>");
            sb.append(item);
            sb.append("</li>");
        }
        sb.append("</ol>");
        return sb.toString();
    }
}
