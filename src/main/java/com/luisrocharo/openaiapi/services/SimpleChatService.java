package com.luisrocharo.openaiapi.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisrocharo.openaiapi.dto.SimpleChatModelResponse;

@Service
public class SimpleChatService {
    
    @Autowired
    private ChatClient chatClient;

    public SimpleChatModelResponse chat(String input) {
        return chatClient
            .prompt(input)
            .call()
            .entity(SimpleChatModelResponse.class);
    }
}
