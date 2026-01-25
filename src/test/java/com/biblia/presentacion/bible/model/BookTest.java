package com.biblia.presentacion.bible.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    void shouldReturnChapter() {
        Map<String, Object> rawChapters = Map.of(
                "1", Map.of(
                        "1", "Verso uno",
                        "2", "Verso dos"
                )
        );

        Book book = new Book(rawChapters);

        Chapter chapter = book.getChapter("1");

        assertThat(chapter).isNotNull();
        assertThat(chapter.getVerse("2")).isEqualTo("Verso dos");
    }
}

