package com.example.learn_english_with_ia.controller;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.repository.SessionRepository;
import com.example.learn_english_with_ia.service.MessageService;
import com.example.learn_english_with_ia.service.SessionService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Validated
public class MessageController {

    private final SessionService sessionService;

    private final MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long sessionId, @RequestParam String text) {
        // 1. On récupère la session (on aura besoin d'un SessionRepository ou Service ici)
        Session session = sessionService.findById(sessionId);

        // 2. On enregistre le message de l'utilisateur
        messageService.creerNouveauMessage(session, text);

        // 3. On génère, enregistre et renvoie la réponse de l'IA !
        return messageService.genererReponseIa(session, text);
    }

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

}
