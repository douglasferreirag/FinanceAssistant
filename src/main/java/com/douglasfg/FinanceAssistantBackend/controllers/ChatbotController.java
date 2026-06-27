package com.douglasfg.FinanceAssistantBackend.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.douglasfg.FinanceAssistantBackend.services.ChatbotService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chatbot")
@Data
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;


    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(
            @RequestParam int month,
            @RequestParam int year) {

        return ResponseEntity.ok(
                chatbotService.analyzeExpenses(month, year)
        );
    }
}
