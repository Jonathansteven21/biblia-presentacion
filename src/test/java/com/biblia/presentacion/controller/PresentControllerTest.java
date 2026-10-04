package com.biblia.presentacion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PresentController.class)
class PresentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void presentacionPageLoads() throws Exception {
        mockMvc.perform(get("/presentacion"))
                .andExpect(status().isOk())
                .andExpect(view().name("presentacion"));
    }
}
