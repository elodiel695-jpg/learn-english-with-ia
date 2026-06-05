package com.example.learn_english_with_ia.service;

import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
