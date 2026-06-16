package com.example.learn_english_with_ia.service;

import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session creerNouvelleSession(String theme) {
        Session testSession = new Session();
        testSession.setTheme(theme);
        testSession.setDate(java.time.LocalDateTime.now());
        testSession.setStatut(com.example.learn_english_with_ia.model.StatutSession.EN_COURS);

        return sessionRepository.save(testSession);
    }

    public List<Session> getAllSession(){
        return sessionRepository.findAll();
    }

    public Session findById(Long id) {
        // 1. Le repository renvoie bien son Optional
        return sessionRepository.findById(id)
                // 2. Le service applique le .orElseThrow() pour déballer l'Optional !
                .orElseThrow(() -> new IllegalArgumentException("Session introuvable"));
    }
}
