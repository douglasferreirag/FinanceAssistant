package com.douglasfg.FinanceAssistantBackend.controllers;





import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.services.ExpenseService;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@Data
public class ExpenseController {


    private final ExpenseService expenseService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Expense expense) {
        try {
            Expense saved = expenseService.save(expense);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            // retorna erro 400 se a categoria não existir
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public List<Expense> findAll() {
        // ✅ Isso dispara o select no banco
        return expenseService.findAll();
    }
 
    // (Opcional) Buscar gasto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> search(@PathVariable Long id) {
        return expenseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}