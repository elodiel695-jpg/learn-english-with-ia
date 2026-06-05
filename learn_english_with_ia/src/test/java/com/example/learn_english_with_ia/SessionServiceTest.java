package com.example.learn_english_with_ia;

import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.model.StatutSession;
import com.example.learn_english_with_ia.repository.SessionRepository;
import com.example.learn_english_with_ia.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    @Test
    void testCreerNouvelleSession(){
        //GIVEN
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        Mockito.when(sessionRepository.save(Mockito.any(Session.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //WHEN
        Session result = sessionService.creerNouvelleSession(session.getTheme());

        //THEN
        assertEquals("Voyage", result.getTheme());
        // On vérifie que le statut est bien forcé à EN_COURS
        assertEquals(StatutSession.EN_COURS, result.getStatut());
        // On vérifie que le service a bien généré une date (qu'elle n'est pas vide)
        org.junit.jupiter.api.Assertions.assertNotNull(result.getDate());
    }

    @Test
    void testRecupererToutesLesSessions() {

        //GIVEN
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        List<Session> listeSession = new ArrayList<>();
        listeSession.add(session);

        Mockito.when(sessionRepository.findAll()).thenReturn(listeSession);

        //WHEN
        List<Session> result = sessionService.getAllSession();

        //THEN
        assertEquals(1, result.size());
        assertEquals(listeSession, result);
        assertEquals("Voyage", result.get(0).getTheme());
    }
}
