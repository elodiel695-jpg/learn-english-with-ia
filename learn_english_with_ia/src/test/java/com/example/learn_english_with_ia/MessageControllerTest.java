package com.example.learn_english_with_ia;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.RoleExpediteur;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.model.StatutSession;
import com.example.learn_english_with_ia.repository.MessageRepository;
import com.example.learn_english_with_ia.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp(){
        messageRepository.deleteAll();
    }

    @Test
    void testGetAllMessages() throws Exception {
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        Message message = new Message();
        message.setMessage("Hello");
        message.setDateEtHeure(LocalDateTime.now());
        message.setExpediteur(RoleExpediteur.USER);
        message.setSession(session);

        sessionRepository.save(session);
        messageRepository.save(message);

        mockMvc.perform(get("/api/messages/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].message").value("Hello"));
    }

    @Test
    void testCreateMessage() throws Exception {
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        Message message = new Message();
        message.setMessage("Hello");
        message.setDateEtHeure(LocalDateTime.now());
        message.setExpediteur(RoleExpediteur.USER);
        message.setSession(session);

        sessionRepository.save(session);
        messageRepository.save(message);

        mockMvc.perform(get("/api/messages/send").param("text", "Hello"));
    }

}
