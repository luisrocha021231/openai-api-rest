package com.luisrocharo.openaiapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

        @Autowired
        private OpenAIClient openAIClient;

        public String processCodeTranslationQuestion(String originalLanguage, String targetLanguage, String content)
                        throws Exception {
                Map<String, String> payload = new HashMap<>();
                payload.put("originalLanguage", originalLanguage);
                payload.put("targetLanguage", targetLanguage);
                payload.put("message", "Translate the following code snippet from " + originalLanguage + " to "
                                + targetLanguage
                                + ". Return only the translated code with no explanations."
                                + " Before translating, verify if the content is valid " + originalLanguage + " code."
                                + " If it is not valid " + originalLanguage + " code, do not translate."
                                + " In that case, return exactly this message (without quotes): El codigo ingresado no es un codigo valido de"
                                + originalLanguage + ".");
                payload.put("content", content);

                ObjectMapper mapper = new ObjectMapper();
                String jsonMessage = mapper.writeValueAsString(payload);

                ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                                .model(ChatModel.GPT_3_5_TURBO)
                                .addUserMessage(jsonMessage)
                                .build();

                ChatCompletion completion = openAIClient.chat().completions().create(params);
                return completion.choices().get(0).message().content().orElse("No response from OpenAI");
        }
}
