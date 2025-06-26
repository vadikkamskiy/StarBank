package com.skypro.StarBank.controller;

import com.skypro.StarBank.model.RecommendationDTO;
import com.skypro.StarBank.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{user_id}")
    public Response getRecommendations(@PathVariable("user_id") String userId) {
        List<RecommendationDTO> recommendations = recommendationService.getRecommendations(userId);
        return new Response(userId, recommendations);
    }

    private static class Response {
        private String user_id;
        private List<RecommendationDTO> recommendations;

        public Response(String user_id, List<RecommendationDTO> recommendations) {
            this.user_id = user_id;
            this.recommendations = recommendations;
        }

        public String getUser_id() { return user_id; }
        public List<RecommendationDTO> getRecommendations() { return recommendations; }
    }
}
