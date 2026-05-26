package com.example.learn_english_with_ia.model;

import jakarta.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScoreGlobal score;

    private String texteAnalyseIA;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
