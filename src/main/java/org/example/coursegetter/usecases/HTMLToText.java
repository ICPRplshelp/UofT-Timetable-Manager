package org.example.coursegetter.usecases;

import org.jsoup.Jsoup;

public class HTMLToText {

    // https://stackoverflow.com/a/3149645
    public String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
