package com.biblia.presentacion.bible.api;

public record VerseResponse(
        String book,
        String chapter,
        String verse,
        String text
) {}
