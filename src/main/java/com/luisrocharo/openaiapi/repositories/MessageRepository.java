package com.luisrocharo.openaiapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisrocharo.openaiapi.entities.ChatEntity;
import com.luisrocharo.openaiapi.entities.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {

    List<MessageEntity> findByChat (ChatEntity chat);
}
