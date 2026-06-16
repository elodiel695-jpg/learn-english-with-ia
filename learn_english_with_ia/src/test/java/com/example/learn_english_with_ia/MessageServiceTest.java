package com.example.learn_english_with_ia;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.RoleExpediteur;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.model.StatutSession;
import com.example.learn_english_with_ia.repository.MessageRepository;
import com.example.learn_english_with_ia.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private dev.langchain4j.model.chat.ChatLanguageModel chatLanguageModel;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    void testCreerNouveauMessage() {
        //GIVEN
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        Message message = new Message();
        message.setMessage("Hello");
        message.setDateEtHeure(LocalDateTime.now());
        message.setExpediteur(RoleExpediteur.USER);
        message.setSession(session);

        Mockito.when(messageRepository.save(Mockito.any(Message.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //WHEN
        Message result = messageService.creerNouveauMessage(session, "Hello");

        //THEN
        assertEquals("Hello", result.getMessage());
        // On vérifie que l'expediteur est bien forcé à USER
        assertEquals(RoleExpediteur.USER, result.getExpediteur());
        assertNotNull(result.getDateEtHeure());
    }

    @Test
    void testGetAllMessage() {
        //GIVEN
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        Message messageUser = new Message();
        messageUser.setMessage("Hello");
        messageUser.setDateEtHeure(LocalDateTime.now());
        messageUser.setExpediteur(RoleExpediteur.USER);
        messageUser.setSession(session);

        Message messageIA = new Message();
        messageIA.setMessage("Hi human");
        messageIA.setDateEtHeure(LocalDateTime.now());
        messageIA.setExpediteur(RoleExpediteur.IA);
        messageIA.setSession(session);

        List<Message> messageList = List.of(messageUser, messageIA);

        Mockito.when(messageRepository.findAll()).thenReturn(messageList);

        //WHEN
        List<Message> result = messageService.getAllMessages();

        //THEN
        assertEquals(2, result.size());
        assertEquals("Hello", result.get(0).getMessage());
        // On vérifie que l'expediteur est bien forcé à USER
        assertEquals(RoleExpediteur.USER, result.get(0).getExpediteur());
        assertNotNull(result.get(0).getDateEtHeure());
    }

    @Test
    void testCreerNouveauMessageAvecReponseIA() {
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        Message message = new Message();
        message.setMessage("Hello");
        message.setDateEtHeure(LocalDateTime.now());
        message.setExpediteur(RoleExpediteur.USER);
        message.setSession(session);
        // Quand l'IA reçoit n'importe quel texte, elle doit répondre "Hi John!"
        Mockito.when(chatLanguageModel.generate(Mockito.anyString())).thenReturn("Hi John!");

        // Mock de repository habituel
        Mockito.when(messageRepository.save(Mockito.any(Message.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // On imagine que notre service va nous renvoyer la réponse de l'IA !
        Message reponseIa = messageService.genererReponseIa(session, "Hello");

        assertEquals("Hi John!", reponseIa.getMessage());
        assertEquals(RoleExpediteur.IA, reponseIa.getExpediteur());
    }

}
