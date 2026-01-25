package com.biblia.presentacion.bible.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BibleTest {

    @Test
    void shouldAddBookViaJsonAnySetter() {
        Bible bible = new Bible();

        Map<String, Object> chapters = Map.of(
                "1", Map.of(
                        "1", "Texto"
                )
        );

        bible.addBook("Génesis", chapters);

        assertThat(bible.getBooks()).containsKey("Génesis");
        assertThat(
                bible.getBooks()
                        .get("Génesis")
                        .getChapter("1")
                        .getVerse("1")
        ).isEqualTo("Texto");
    }
}
