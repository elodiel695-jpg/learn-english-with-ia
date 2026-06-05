package com.example.learn_english_with_ia.controller;

import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.service.SessionService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@Validated
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/start")
    public Session startSession(@NotBlank(message = "Le thème est obligatoire") @RequestParam String theme ){
        return sessionService.creerNouvelleSession(theme);
    }

    // Une méthode temporaire en GET pour créer une session depuis le navigateur
    @GetMapping("/create-test")
    public Session createTestSession(@RequestParam String theme) {
        return sessionService.creerNouvelleSession(theme);
    }

    @GetMapping("/all")
    public List<Session> getAllSession(){
        return sessionService.getAllSession();
    }
}
