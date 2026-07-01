package com.douglasfg.FinanceAssistantBackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.services.ChatbotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    // Analisar despesas → 200 OK se resultado, 204 No Content se vazio
    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(
            @RequestParam int month,
            @RequestParam int year) {

        String result = chatbotService.analyzeExpenses(month, year);

        if (result == null || result.isBlank()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(result); // 200 OK
    }
}
