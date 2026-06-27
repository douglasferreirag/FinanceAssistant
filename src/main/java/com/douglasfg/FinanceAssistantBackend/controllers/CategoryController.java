package com.douglasfg.FinanceAssistantBackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.douglasfg.FinanceAssistantBackend.entities.Category;
import com.douglasfg.FinanceAssistantBackend.services.CategoryService;

import lombok.Data;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/categories")
@Data
@RequiredArgsConstructor

public class CategoryController {


    
    private final CategoryService categoryService;
 
   

   @PostMapping("/save")
   public ResponseEntity<Category> save(@RequestBody Category category) {
        Category saved = categoryService.save(category.getName());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/findAll")
    public List<Category> findAll() {
        return categoryService.findAll();
    }
    
}
