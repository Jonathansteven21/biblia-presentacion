package com.biblia.presentacion.bible.service;

import com.biblia.presentacion.bible.model.Bible;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;

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
}
