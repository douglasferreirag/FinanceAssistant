package com.douglasfg.FinanceAssistantBackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalExpense {

    @EmbeddedId
    private GoalExpenseId id;

    @ManyToOne
    @MapsId("goalId")
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @ManyToOne
    @MapsId("expenseId")
    @JoinColumn(name = "expense_id")
    private Expense expense;

    // Campo extra opcional para auditoria
    private LocalDate linkedAt ;
}
