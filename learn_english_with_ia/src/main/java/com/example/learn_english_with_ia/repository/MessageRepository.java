package com.example.learn_english_with_ia.repository;

import com.example.learn_english_with_ia.model.Message;
import com.example.learn_english_with_ia.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySession(Session session);
}
