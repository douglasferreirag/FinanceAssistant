package com.douglasfg.FinanceAssistantBackend.repositories;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;



public interface GoalRepository extends JpaRepository<Goal, Long> {
   
    @Query("SELECT g FROM Goal g WHERE g.month = :month AND g.year = :year")
    Optional<Goal> findByMonthAndYear(int month, int year);
    
}
