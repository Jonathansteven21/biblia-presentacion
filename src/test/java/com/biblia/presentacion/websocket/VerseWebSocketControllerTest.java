package com.biblia.presentacion.websocket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VerseWebSocketControllerTest {

    @Autowired
    private VerseWebSocketController controller;

    @Test
    void verseWebSocketControllerIsLoaded() {
        assertThat(controller).isNotNull();
    }
}
