package com.example.learn_english_with_ia;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

public class AiTest {
    public static void main(String[] args) {
        // 1. On configure la connexion vers Ollama (qui tourne sur le port 11434)
        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3.2:3b")
                .temperature(0.7) // Règle la créativité de l'IA (0 = très carré, 1 = très créatif)
                .build();

        System.out.println("Connexion à l'IA en cours...");

        // 2. On envoie notre premier prompt de test
        String reponse = model.generate("Hello! Act as an English teacher. Say a welcoming word.");

        // 3. On affiche le résultat dans la console Java
        System.out.println("--- Réponse de l'IA ---");
        System.out.println(reponse);
    }
}
