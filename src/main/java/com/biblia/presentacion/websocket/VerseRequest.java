package com.biblia.presentacion.websocket;

public record VerseRequest(
        String book,
        String chapter,
        int from,
        int to
) {}
