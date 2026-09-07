package com.example.learn_english_with_ia;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.Report;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.model.StatutSession;
import com.example.learn_english_with_ia.repository.ReportRepository;
import com.example.learn_english_with_ia.service.ReportService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private ChatLanguageModel chatLanguageModel;

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    void testGenererRapportFinDeSession(){
        //GIVEN
        // 1. La session de départ
        Session session = new Session();
        session.setId(1L);

        // 2. On dresse le faux robot IA
        Mockito.when(chatLanguageModel.generate(Mockito.anyString())).thenReturn("Analyse IA de test");

        // 3. On dresse la fausse base de données (le save renvoie l'objet qu'il reçoit)
        Mockito.when(reportRepository.save(Mockito.any(Report.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //WHEN
        Report result = reportService.genererRapport(session);

        //THEN
        assertNotNull(result);

        assertEquals("Analyse IA de test", result.getTexteAnalyseIA());

        assertEquals(1L, result.getSession().getId());
    }
}
