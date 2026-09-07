package com.example.learn_english_with_ia.service;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.Report;
import com.example.learn_english_with_ia.model.ScoreGlobal;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.repository.MessageRepository;
import com.example.learn_english_with_ia.repository.ReportRepository;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final MessageRepository messageRepository;

    private final ChatLanguageModel chatLanguageModel;

    public Report genererRapport(Session session) {
        List<Message> messages = messageRepository.findBySession(session);

        String texteConversation = messages.stream()
                .map(Message::getMessage)
                .collect(Collectors.joining("\n"));

        String analyse = chatLanguageModel.generate("Analyse cette session : " + texteConversation);

        Report newReport = new Report();
        newReport.setTexteAnalyseIA(analyse);
        newReport.setScore(ScoreGlobal.B1);
        newReport.setSession(session);
        return reportRepository.save(newReport);
    }
}
