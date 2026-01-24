package com.biblia.presentacion.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class VerseWebSocketController {

    @MessageMapping("/verse")
    @SendTo("/topic/verse")
    public String sendDummyVerse(String message) {
        return message;
    }
}
