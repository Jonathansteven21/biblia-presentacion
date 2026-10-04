package com.biblia.presentacion.bible.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChapterTest {

    @Test
    void shouldReturnVerse() {
        Map<String, String> verses = Map.of(
                "1", "En el principio",
                "2", "Creó Dios"
        );

        Chapter chapter = new Chapter(verses);

        assertThat(chapter.getVerse("1")).isEqualTo("En el principio");
    }
}
