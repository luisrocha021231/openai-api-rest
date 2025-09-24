package com.luisrocharo.openaiapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisrocharo.openaiapi.dto.CodeTranslatorRequest;
import com.luisrocharo.openaiapi.dto.CodeTranslatorResponse;
import com.luisrocharo.openaiapi.services.CodeTranslatorService;

@RestController
@RequestMapping("/api/code-translator")
@CrossOrigin
public class CodeTranslatorController {
    
    @Autowired
    private CodeTranslatorService codeTranslatorService;

    @PostMapping("/translate")
    public ResponseEntity<CodeTranslatorResponse> generate(@RequestBody CodeTranslatorRequest request) {
        return ResponseEntity.ok(codeTranslatorService.translate(request.getSourceLanguage(), request.getTargetLanguage(), request.getCodeSnippet()));
    }
}
