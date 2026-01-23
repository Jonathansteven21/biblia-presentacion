package com.biblia.presentacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PresentController {

    @GetMapping("/presentacion")
    public String presentacion() {
        return "presentacion";
    }
}
