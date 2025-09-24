package com.luisrocharo.openaiapi.services;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisrocharo.openaiapi.dto.CodeTranslatorResponse;
import com.luisrocharo.openaiapi.templates.PromptLibrary;

@Service
public class CodeTranslatorService {
    
    @Autowired
    private ChatClient chatClient;

    public CodeTranslatorResponse translate(String sourceCode, String targetCode, String code) {
        Prompt prompt = PromptLibrary.CODE_TRANSLATOR_PROMPT_TEMPLATE.create(
            Map.of(
                "sourceCode", sourceCode,
                "targetCode", targetCode,
                "code", code
             )
        );

        return chatClient
            .prompt(prompt)
            .call()
            .entity(CodeTranslatorResponse.class);
    }
}
