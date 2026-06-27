package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasfg.FinanceAssistantBackend.services.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense-dashboard")
@RequiredArgsConstructor
public class DashboardExpenseController {

     
    private final ExpenseService expenseService;

    @GetMapping("/expenses-by-category")
    public Map<String, Double> getExpensesByCategory() {
        return expenseService.getExpensesGroupedByCategory();
    }
    
}
