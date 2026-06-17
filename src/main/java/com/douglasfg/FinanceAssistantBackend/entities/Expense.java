package com.douglasfg.FinanceAssistantBackend.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    @Id 
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Descrição não pode estar vazia")
    private String description;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private Double cost;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expense_date")
    @NotNull(message = "A data é obrigatória")
    private LocalDate expenseDate;

    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Categoria é obrigatória")
    @JsonBackReference
    private Category category;


     public Expense(String description, Double cost, LocalDate expenseDate, Category category) {
        this.description = description;
        this.cost = cost;
        this.expenseDate = expenseDate;
        this.category = category;
    }

   
}
