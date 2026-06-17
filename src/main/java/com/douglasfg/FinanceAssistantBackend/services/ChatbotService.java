package com.douglasfg.FinanceAssistantBackend.services;


import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.repositories.ExpenseRepository;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalRepository;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestClientException;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
@Data
public class ChatbotService {

    private final ExpenseRepository expenseRepository;
    private final GoalRepository goalRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    public ChatbotService(
            ExpenseRepository expenseRepository,
            GoalRepository goalRepository) {

        this.expenseRepository = expenseRepository;
        this.goalRepository = goalRepository;
    }

    public String analyzeExpenses(int month, int year) {

        try {
                List<Expense> expenses = expenseRepository.findByMonthAndYear(month, year);
                double total = expenses.stream().mapToDouble(Expense::getCost).sum();
                double goal = goalRepository.findByMonthAndYear(month, year)
                        .map(t -> t.getLimit_value())
                        .orElse(0.0);

                String prompt = """
                Você é um consultor financeiro especializado.

                Analise os dados abaixo:

                Mês: %d
                Ano: %d
                Total gasto: %.2f
                Meta definida: %.2f

                Explique em uma única resposta textual se a meta foi ultrapassada ou não,
                e dê recomendações práticas para melhorar o controle financeiro.
                """.formatted(month, year, total, goal);

                String url = GEMINI_API_URL + apiKey;

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> body = Map.of(
                        "contents", List.of(
                                Map.of("parts", List.of(Map.of("text", prompt)))
                        )
                );

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
                ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

                JsonNode geminiResponse = objectMapper.readTree(response.getBody());
                return geminiResponse.path("candidates").get(0)
                        .path("content").path("parts").get(0)
                        .path("text").asString();

        } catch (RestClientException | JacksonException e) {
                return "⚠️ Erro ao processar análise: " + e.getMessage();
        }
    }

    public String listModels() {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models?key="
                        + apiKey;

        return restTemplate.getForObject(url, String.class);
    }
}