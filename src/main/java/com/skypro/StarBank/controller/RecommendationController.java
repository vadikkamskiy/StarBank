package com.skypro.StarBank.controller;

import com.skypro.StarBank.model.Product;
import com.skypro.StarBank.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Product> getRecommendations(@PathVariable UUID userId) {
        return recommendationService.recommendForUser(userId);
    }
}
