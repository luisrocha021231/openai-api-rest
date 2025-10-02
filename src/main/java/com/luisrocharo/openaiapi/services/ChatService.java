package com.luisrocharo.openaiapi.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisrocharo.openaiapi.entities.ChatEntity;
import com.luisrocharo.openaiapi.entities.MessageEntity;
import com.luisrocharo.openaiapi.entities.UserEntity;
import com.luisrocharo.openaiapi.repositories.ChatRepository;
import com.luisrocharo.openaiapi.repositories.MessageRepository;
import com.luisrocharo.openaiapi.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatClient chatClient;

    @Transactional
    public ChatEntity createChat(String userId) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not founded"));

        ChatEntity chat = ChatEntity.builder()
            .user(user)
            .lastMessage("Hola, ¿en qué puedo ayudarte?")
            .name("Nuevo chat")
            .build();

        return chatRepository.save(chat);
    }

    public MessageEntity sendMessage(String chatId, String text, String sender) {
        ChatEntity chat = chatRepository.findById(chatId)
            .orElseThrow(() -> new RuntimeException("Chat not founded"));

            // GUARDA EL MENSAJE DEL USUARIO
            MessageEntity userMsg = MessageEntity.builder()
                .text(text)
                .sender(sender)
                .chat(chat)
                .build();
            chat.addMessage(userMsg);
            messageRepository.save(userMsg);

            // SE OBTIENE LA RESPUESTA DE LA IA
            String aiResponse = chatClient.prompt()
                .user(text)
                .call()
                .content();

            // SE GUARDA EL MENSAJE DE LA IA
            MessageEntity aiMsg = MessageEntity.builder()
                .text(aiResponse)
                .sender("ai")
                .chat(chat)
                .build();
            chat.addMessage(aiMsg);
            messageRepository.save(aiMsg);

            // SE ACTUALIZA EL CHAT
            chatRepository.save(chat);

            return aiMsg;
    }
}
