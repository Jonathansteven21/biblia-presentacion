package com.biblia.presentacion.bible.model;

import java.util.HashMap;
import java.util.Map;

public class Book {

    private final Map<String, Chapter> chapters = new HashMap<>();

    @SuppressWarnings("unchecked")
    public Book(Map<String, Object> rawChapters) {
        rawChapters.forEach((chapterNumber, chapterValue) -> {
            if (chapterValue instanceof Map<?, ?> verses) {
                chapters.put(
                        chapterNumber,
                        new Chapter((Map<String, String>) verses)
                );
            }
        });
    }

    public Chapter getChapter(String number) {
        return chapters.get(number);
    }

    public Map<String, Chapter> getChapters() {
        return chapters;
    }

}

