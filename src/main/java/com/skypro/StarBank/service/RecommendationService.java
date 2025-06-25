package com.skypro.StarBank.service;

import com.skypro.StarBank.model.RecommendationDTO;
import com.skypro.StarBank.repository.RecommendationRepository;
import com.skypro.StarBank.rules.RecommendationRuleSet;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final List<RecommendationRuleSet> rules;
    private final RecommendationRepository repository;

    public RecommendationService(List<RecommendationRuleSet> rules, RecommendationRepository repository) {
        this.rules = rules;
        this.repository = repository;
    }

    public List<RecommendationDTO> getRecommendations(String userId) {
        return rules.stream()
                .map(rule -> rule.check(userId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}