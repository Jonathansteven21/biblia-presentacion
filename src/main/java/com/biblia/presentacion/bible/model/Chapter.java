package com.biblia.presentacion.bible.model;

import java.util.Map;

public class Chapter {

    private final Map<String, String> verses;

    public Chapter(Map<String, String> verses) {
        this.verses = verses;
    }

    public String getVerse(String number) {
        return verses.get(number);
    }
}
