package com.luisrocharo.openaiapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luisrocharo.openaiapi.dto.QuestionRequest;
import com.luisrocharo.openaiapi.services.OpenAIService;

import java.util.Map;

@RestController
@RequestMapping("public/api/openai")
@CrossOrigin
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("code-translation")
    public ResponseEntity<Map<String, String>> codeTranslation(@RequestBody QuestionRequest questionRequest) {
        try {
            String result = openAIService.processCodeTranslationQuestion(
                    questionRequest.getOriginalLanguage(),
                    questionRequest.getTargetLanguage(),
                    questionRequest.getContent());
            return ResponseEntity.ok(Map.of("result", result));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
