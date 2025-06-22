package com.skypro.starbank.controller;

import com.skypro.starbank.service.RecommendationService;
import com.skypro.starbank.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Product> getRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendForUser(userId);
    }
}
