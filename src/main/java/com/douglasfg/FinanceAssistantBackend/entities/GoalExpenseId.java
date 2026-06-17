package com.douglasfg.FinanceAssistantBackend.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalExpenseId implements Serializable {
    private Long goalId;
    private Long expenseId;
}
