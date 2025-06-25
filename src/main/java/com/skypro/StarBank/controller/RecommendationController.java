package com.skypro.StarBank.controller;

import com.skypro.StarBank.model.Product;
import com.skypro.StarBank.service.RecommendationService;
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
    public ResponseEntity<User> getRecommendations(@PathVariable("userId") UUID userId) {
        User user = recommendationService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/AllUsers")
    public List<User> getAllUsers() {
        return recommendationService.getAllUsers();
    }
}
