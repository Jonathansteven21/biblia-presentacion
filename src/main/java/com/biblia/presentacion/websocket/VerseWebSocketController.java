package com.biblia.presentacion.websocket;

import com.biblia.presentacion.bible.service.BibleService;
import com.biblia.presentacion.bible.api.VerseResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VerseWebSocketController {

    private final BibleService bibleService;

    public VerseWebSocketController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @MessageMapping("/verse")
    @SendTo("/topic/verse")
    public VerseMessage sendVerse(VerseRequest request) {

        List<VerseResponse> verses = bibleService.getVerseText(
                request.book(),
                request.chapter(),
                request.from(),
                request.to()
        );

        String reference = buildReference(
                request.book(),
                request.chapter(),
                request.from(),
                request.to()
        );

        String text = verses.stream()
                .map(VerseResponse::text)
                .collect(Collectors.joining(" "));

        return new VerseMessage(reference, text);
    }

    private String buildReference(String book, String chapter, int from, int to) {
        if (from == to) {
            return book + " " + chapter + ":" + from;
        }
        return book + " " + chapter + ":" + from + "-" + to;
    }
}
