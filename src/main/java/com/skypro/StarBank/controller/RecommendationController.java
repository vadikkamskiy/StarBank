package com.skypro.StarBank.controller;

import com.skypro.StarBank.service.RecommendationService;
import com.skypro.StarBank.model.Product;
import com.skypro.StarBank.model.Transaction;
import com.skypro.StarBank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getRecommendations(@PathVariable("userId") UUID userId) {
        List<Product> recommendations = recommendationService.getRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }
    @GetMapping("/AllUsers")
    public List<User> getAllUsers() {
        return recommendationService.getAllUsers();
    }

    @GetMapping("/getTransaction/{userId}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable("userId") UUID userId) {
        List<Transaction> transactions = recommendationService.getTransaction(userId);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }
    
}
