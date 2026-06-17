package com.douglasfg.backend.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.repositories.CategoryRepository;
import com.douglasfg.FinanceAssistantBackend.repositories.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

   
    public Expense save(Expense expense) {
            // se expense tem um objeto Category
        Optional<Category> categoryOpt = categoryRepository.findByName(expense.getCategory().getName());

        if (categoryOpt.isEmpty()) {
            throw new IllegalArgumentException("Categoria não encontrada: " + expense.getCategory().getName());
        }

        Category category = categoryOpt.get();
        expense.setCategory(category); // aqui expense deve ter um campo Category, não String

        return expenseRepository.save(expense);
    }
    // ✅ Listar todos os gastos
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    // ✅ Buscar gasto por ID
    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public Double sumByMonthAndYear(int month, int year) {
        Double total = expenseRepository.sumByMonthAndYear(month, year);
        return total != null ? total : 0.0;
    }

   
} 