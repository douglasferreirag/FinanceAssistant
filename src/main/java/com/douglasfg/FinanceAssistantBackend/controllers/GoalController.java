package com.douglasfg.FinanceAssistantBackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.douglasfg.FinanceAssistantBackend.entities.Goal;
import com.douglasfg.FinanceAssistantBackend.services.GoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/save")
    public Goal save(@RequestBody Goal goal) {
        return goalService.save(goal);
    }

    @GetMapping("/findAll")
    public List<Goal> findAll() {
        return goalService.findAll();
    }

   

    

   
}
