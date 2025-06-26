package com.skypro.StarBank.rules;

import com.skypro.StarBank.model.RecommendationDTO;

import java.util.Optional;

public interface RecommendationRuleSet {
    Optional<RecommendationDTO> check(String userId);
}