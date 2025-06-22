package com.skypro.starbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import com.skypro.starbank.repository.TransactionRepository;
import com.skypro.starbank.repository.ProductRepository;
import com.skypro.starbank.model.Transaction;
import com.skypro.starbank.model.Product;


@Service
public class RecommendationService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<Product> recommendForUser(Long userId) {
        List<Transaction> transactions = transactionRepo.getTransactionsByUserId(userId);
        double totalAmount = transactions.stream()
                                         .mapToDouble(Transaction::getAmount)
                                         .sum();

        if (totalAmount > 10000) {
            return productRepo.getAllProducts().stream()
                .filter(p -> p.getType().equalsIgnoreCase("premium"))
                .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
