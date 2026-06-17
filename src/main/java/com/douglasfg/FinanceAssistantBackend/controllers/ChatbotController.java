package com.douglasfg.FinanceAssistantBackend.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.douglasfg.FinanceAssistantBackend.services.ChatbotService;

import lombok.Data;

@RestController
@RequestMapping("/api/chatbot")
@Data
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(
            @RequestParam int month,
            @RequestParam int year) {

        return ResponseEntity.ok(
                chatbotService.analyzeExpenses(month, year)
        );
    }
}
