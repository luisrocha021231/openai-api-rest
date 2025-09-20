package com.luisrocharo.openaiapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String originalLanguage;
    private String targetLanguage;
    private String content;
}
