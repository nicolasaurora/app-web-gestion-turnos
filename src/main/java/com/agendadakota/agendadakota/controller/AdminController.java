package com.agendadakota.agendadakota.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String admin(Authentication auth) {
        return "Hola " + auth.getName();
    }
}

