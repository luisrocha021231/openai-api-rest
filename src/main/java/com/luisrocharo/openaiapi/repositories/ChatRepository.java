package com.luisrocharo.openaiapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisrocharo.openaiapi.entities.ChatEntity;
import com.luisrocharo.openaiapi.entities.UserEntity;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, String> {

    List<ChatEntity> findByUser (UserEntity user);
}
