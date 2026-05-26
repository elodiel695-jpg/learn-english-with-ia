package com.example.learn_english_with_ia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime dateEtHeure;

    @Enumerated(EnumType.STRING)
    private RoleExpediteur expediteur;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
