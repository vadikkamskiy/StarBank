package com.skypro.StarBank.service;

import com.skypro.StarBank.model.Product;
import com.skypro.StarBank.model.Transaction;
import com.skypro.StarBank.model.User;
import com.skypro.StarBank.repository.ProductRepository;
import com.skypro.StarBank.repository.TransactionRepository;
import com.skypro.StarBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RecommendationService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Product> recommendForUser(UUID userId) {
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

    public User getUserById(UUID userId) {
        return userRepo.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public List<Transaction> getTransaction(UUID userId) {
        return transactionRepo.getTransactionsByUserId(userId);
    }

    public List<Product> getRecommendations(UUID userId){
        return productRepo.findRecommendations(userId);
    }
}
