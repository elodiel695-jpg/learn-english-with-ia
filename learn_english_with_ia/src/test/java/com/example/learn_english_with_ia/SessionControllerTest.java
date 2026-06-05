package com.example.learn_english_with_ia;

import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.model.StatutSession;
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
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp(){
        sessionRepository.deleteAll();
    }

    @Test
    void testGetAllSessions() throws Exception {
        Session session = new Session();
        session.setTheme("Voyage");
        session.setDate(LocalDateTime.now());
        session.setStatut(StatutSession.EN_COURS);

        sessionRepository.save(session);

        mockMvc.perform(get("/api/sessions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].theme").value("Voyage"));
    }
}
