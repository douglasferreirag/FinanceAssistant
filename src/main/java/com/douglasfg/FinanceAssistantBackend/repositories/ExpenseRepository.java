package com.douglasfg.FinanceAssistantBackend.repositories;





import com.douglasfg.FinanceAssistantBackend.entities.Expense;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT COALESCE(SUM(e.cost), 0) FROM Expense e WHERE MONTH(e.expenseDate) = :month AND YEAR(e.expenseDate) = :year")
    Double sumByMonthAndYear(int month, int year);


    @Query("SELECT e.category.name, SUM(e.cost) FROM Expense e GROUP BY e.category.name")
    List<Object[]> getExpensesGroupedByCategory();

   

}
