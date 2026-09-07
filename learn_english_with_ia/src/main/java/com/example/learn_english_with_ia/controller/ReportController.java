package com.example.learn_english_with_ia.controller;

import com.example.learn_english_with_ia.model.Report;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.service.ReportService;
import com.example.learn_english_with_ia.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    private final SessionService sessionService;

    @PostMapping("/session/{sessionId}")
    public Report generateReport (@PathVariable Long sessionId) {

        Session session = sessionService.findById(sessionId);

        return reportService.genererRapport(session);

    }
}
