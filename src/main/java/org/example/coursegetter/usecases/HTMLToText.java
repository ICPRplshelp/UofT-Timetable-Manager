package org.example.coursegetter.usecases;

import org.jsoup.Jsoup;

public class HTMLToText {

    // https://stackoverflow.com/a/3149645

    /**
     * Converts HTML to plain text.
     * @param html the HTML to convert.
     * @return the text.
     */
    public String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
