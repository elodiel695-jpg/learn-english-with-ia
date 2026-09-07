# 🇬🇧 Learn English with AI

**Learn English with AI** est une application Spring Boot permettant aux utilisateurs de pratiquer leur anglais de manière interactive et de recevoir une évaluation personnalisée de leur niveau grâce à l'intégration d'un Modèle de Langage (LLM) exécuté en local.

---

## 🛠️ Stack Technique

* **Back-end :** Java, Spring Boot, Spring Data JPA
* **IA & LLM :** LangChain4j, Ollama (Llama 3.2)
* **Base de données :** H2 (Base de données en mémoire)
* **Outillage & Tests :** Maven, Postman

---

## 🚀 Fonctionnalités Principales

* **Gestion des sessions d'apprentissage :** Création de sessions thématiques personnalisées (ex. *Voyage*, *Grammaire*).
* **Échanges de messages :** Enregistrement dynamique des conversations entre l'utilisateur et le système.
* **Analyse & Génération de rapports par IA :**
    * Traitement du contexte global de la conversation via l'API Stream de Java.
    * Envoi dynamique du prompt vers un modèle LLM local via LangChain4j.
    * Génération d'un bilan détaillé (score, analyse du vocabulaire, pistes d'amélioration).

---

## 🏛️ Architecture & Choix Techniques

* **Architecture en couches :** Organisation respectant le découpage *Controller / Service / Repository / Entity*.
* **Persistance adaptée :** Utilisation de `@Column(columnDefinition = "TEXT")` pour gérer le stockage de textes volumineux.
* **Génération de requêtes JPA :** Exploitation des méthodes dérivées de Spring Data JPA (`findBySession`).
* **Traitement de données fonctionnel :** Utilisation des Java Streams (`Collectors.joining`) pour concaténer les messages en un prompt optimisé.

---

## 📋 Prérequis & Installation

1. **Cloner le repository :**
   ```bash
   git clone [https://github.com/elodiel695-jpg/learn-english-with-ia.git](https://github.com/elodiel695-jpg/learn-english-with-ia.git)