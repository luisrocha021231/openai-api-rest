package com.luisrocharo.openaiapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisrocharo.openaiapi.dto.SimpleChatModelResponse;
import com.luisrocharo.openaiapi.services.SimpleChatService;

@RestController
@RequestMapping("/public")
public class SimpleChatController {
    
    @Autowired
    private SimpleChatService simpleChatService;

    @PostMapping("/chat")
    public SimpleChatModelResponse chat(@RequestBody String input) {
        return simpleChatService.chat(input);
    }
}
