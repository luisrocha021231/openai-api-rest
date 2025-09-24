package com.luisrocharo.openaiapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeTranslatorRequest {
    
    private String sourceLanguage;
    private String targetLanguage;
    private String codeSnippet;
}
