package com.douglasfg.FinanceAssistantBackend.repositories;



import java.util.List;

import com.douglasfg.FinanceAssistantBackend.entities.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT COALESCE(SUM(e.cost), 0) FROM Expense e WHERE MONTH(e.expenseDate) = :month AND YEAR(e.expenseDate) = :year")
    Double sumByMonthAndYear(int month, int year);

   
    @Query("SELECT e FROM Expense e WHERE FUNCTION('MONTH', e.expenseDate) = :month AND FUNCTION('YEAR', e.expenseDate) = :year")
    List<Expense> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
   
    @Query("SELECT e.category.name, SUM(e.cost) FROM Expense e GROUP BY e.category.name")
    List<Object[]> getExpensesGroupedByCategory();

}
