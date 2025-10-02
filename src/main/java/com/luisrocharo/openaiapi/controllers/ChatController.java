package com.luisrocharo.openaiapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luisrocharo.openaiapi.entities.ChatEntity;
import com.luisrocharo.openaiapi.entities.MessageEntity;
import com.luisrocharo.openaiapi.services.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatEntity> createChat(@RequestParam String userId) {
        ChatEntity chat = chatService.createChat(userId);
        return ResponseEntity.ok(chat);
    }
    
    @PostMapping("/{chatId}/messages")
    public MessageEntity addMessage(
        @PathVariable String chatId,
        @RequestParam String text,
        @RequestParam (defaultValue = "user") String sender ) {
            return chatService.sendMessage(chatId, text, sender);
    }
}
