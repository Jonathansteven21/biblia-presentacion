package com.biblia.presentacion.bible.service;

import com.biblia.presentacion.bible.api.VerseResponse;
import com.biblia.presentacion.bible.model.Bible;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BibleServiceTest {

    @Autowired
    private BibleService bibleService;

    @Test
    void shouldLoadBibleFromJson() {
        Bible bible = bibleService.getBible();

        assertThat(bible).isNotNull();
        assertThat(bible.getBooks()).isNotEmpty();
    }

    @Test
    void shouldReadRealVerseFromJson() {
        Bible bible = bibleService.getBible();

        String verse = bible
                .getBooks()
                .get("1 Corintios")
                .getChapter("1")
                .getVerse("1");

        assertThat(verse).contains("Pablo");
    }

    @Test
    void shouldReturnVerseRangeUsingServiceApi() {
        // when
        List<VerseResponse> verses = bibleService.getVerseText(
                "1 Corintios",
                "1",
                1,
                3
        );

        // then
        assertThat(verses).isNotEmpty();
        assertThat(verses).hasSize(3);

        assertThat(verses.get(0).verse()).isEqualTo("1");
        assertThat(verses.get(0).text()).contains("Pablo");

        assertThat(verses.get(2).verse()).isEqualTo("3");
    }
}
