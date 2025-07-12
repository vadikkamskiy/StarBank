package com.skypro.StarBank.controller;

import com.skypro.StarBank.dto.request.DynamicRuleRequest;
import com.skypro.StarBank.dto.response.DynamicRuleResponse;
import com.skypro.StarBank.model.DynamicRule;
import com.skypro.StarBank.model.Query;
import com.skypro.StarBank.repository.DynamicRuleRepository;
import com.skypro.StarBank.service.DynamicRuleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rule")
public class DynamicRuleController {
    private final DynamicRuleRepository ruleRepository;
    private final DynamicRuleService ruleService;

    public DynamicRuleController(DynamicRuleRepository ruleRepository, DynamicRuleService ruleService) {
        this.ruleRepository = ruleRepository;
        this.ruleService = ruleService;
    }

    @PostMapping
    public DynamicRuleResponse createRule(@RequestBody DynamicRuleRequest request) {
        return ruleService.createRule(request);
    }



    @GetMapping
    public Map<String, List<DynamicRule>> getAllRules() {
        Map<String, List<DynamicRule>> response = new HashMap<>();
        response.put("data", ruleRepository.findAll());
        return response;
    }

    @DeleteMapping("/{product_id}")
    public void deleteRule(@PathVariable String product_id) {
        ruleRepository.deleteById(product_id);

    }
}
