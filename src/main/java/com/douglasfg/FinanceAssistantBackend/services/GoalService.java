package com.douglasfg.FinanceAssistantBackend.services;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.repositories.GoalRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class GoalService {

    
    private final GoalRepository goalRepository;



    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

   


    
}

