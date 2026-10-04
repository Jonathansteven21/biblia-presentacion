package com.biblia.presentacion.bible.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class Bible {

    private final Map<String, Book> books = new HashMap<>();

    @JsonAnySetter
    @SuppressWarnings("unchecked")
    public void addBook(String bookName, Object value) {
        if (value instanceof Map<?, ?> chapters) {
            books.put(bookName, new Book((Map<String, Object>) chapters));
        }
    }

    public Map<String, Book> getBooks() {
        return books;
    }
}
