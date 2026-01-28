package com.biblia.presentacion.bible.service;

import com.biblia.presentacion.bible.api.VerseResponse;
import com.biblia.presentacion.bible.model.Bible;
import com.biblia.presentacion.bible.model.Book;
import com.biblia.presentacion.bible.model.Chapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class BibleService {

    private final Bible bible;

    public BibleService(ObjectMapper objectMapper) {
        this.bible = loadBible(objectMapper);
    }

    private Bible loadBible(ObjectMapper mapper) {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("bible/rvr1960.json")) {

            if (is == null) {
                throw new IllegalStateException("rvr1960.json not found");
            }

            return mapper.readValue(is, Bible.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load Bible JSON", e);
        }
    }

    public Bible getBible() {
        return bible;
    }

    public List<VerseResponse> getVerseText(
            String book,
            String chapter,
            int from,
            int to
    ) {
        Book bookObj = bible.getBooks().get(book);
        if (bookObj == null) {
            return List.of();
        }

        Chapter chapterObj = bookObj.getChapter(chapter);
        if (chapterObj == null) {
            return List.of();
        }

        List<VerseResponse> result = new ArrayList<>();

        for (int i = from; i <= to; i++) {
            String verseText = chapterObj.getVerse(String.valueOf(i));
            if (verseText != null) {
                result.add(new VerseResponse(
                        book,
                        chapter,
                        String.valueOf(i),
                        verseText
                ));
            }
        }

        return result;
    }
}
