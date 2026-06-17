package com.douglasfg.FinanceAssistantBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatbotResponse {
    private int month;
    private int year;
    private double totalExpenses;
    private double goalLimit;
    private boolean exceeded;
    private String suggestion;

}
