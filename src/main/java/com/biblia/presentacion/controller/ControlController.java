package com.biblia.presentacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlController {

    @GetMapping("/control")
    public String control() {
        return "control";
    }
}
