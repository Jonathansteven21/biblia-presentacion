package com.biblia.presentacion.bible.api;

import com.biblia.presentacion.bible.service.BibleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BibleController {

    private final BibleService bibleService;

    public BibleController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @GetMapping("/api/bible/verses")
    public List<VerseResponse> getVerses(
            @RequestParam String book,
            @RequestParam String chapter,
            @RequestParam int from,
            @RequestParam int to
    ) {
        return bibleService.getVerseText(book, chapter, from, to);
    }

    @GetMapping("/api/bible/books")
    public List<String> getBooks() {
        return bibleService.getBible()
                .getBooks()
                .keySet()
                .stream()
                .sorted()
                .toList();
    }

    @GetMapping("/api/bible/chapters")
    public List<String> getChapters(
            @RequestParam String book
    ) {
        return bibleService.getChapters(book);
    }

}
