package com.example.learn_english_with_ia.service;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.RoleExpediteur;
import com.example.learn_english_with_ia.model.Session;
import com.example.learn_english_with_ia.repository.MessageRepository;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final ChatLanguageModel chatLanguageModel;

    public Message creerNouveauMessage(Session session, String message) {
        Message nouveauMessage = new Message();
        nouveauMessage.setMessage(message);
        nouveauMessage.setDateEtHeure(java.time.LocalDateTime.now());
        nouveauMessage.setExpediteur(RoleExpediteur.USER);
        nouveauMessage.setSession(session);
        return messageRepository.save(nouveauMessage);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message genererReponseIa(Session session, String contenuMessageUtilisateur) {
        // 1. On appelle l'IA (Ollama) pour obtenir sa réponse textuelle
        String texteReponseIa = chatLanguageModel.generate(contenuMessageUtilisateur);

        // 2. On crée l'objet Message pour stocker cette réponse
        Message messageIa = new Message();
        messageIa.setMessage(texteReponseIa);
        messageIa.setDateEtHeure(java.time.LocalDateTime.now());
        messageIa.setExpediteur(RoleExpediteur.IA); // <--- C'est bien l'IA qui parle !
        messageIa.setSession(session);

        // 3. On sauvegarde la réponse de l'IA en base de données
        return messageRepository.save(messageIa);
    }

}
