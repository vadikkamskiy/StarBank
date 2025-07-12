package com.skypro.StarBank.repository;

import com.skypro.StarBank.dto.response.RecommendationDTO;
import com.skypro.StarBank.model.ProductCatalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DynamicRecommendationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductCatalog productCatalog;
    Logger logger = LoggerFactory.getLogger(RecommendationRepository.class);

    public DynamicRecommendationRepository(JdbcTemplate jdbcTemplate, ProductCatalog productCatalog) {
        this.jdbcTemplate = jdbcTemplate;
        this.productCatalog = productCatalog;
    }

    @Cacheable("userProduct")
    public boolean hasUserProductOfType(String userId, String productType) {
        logger.info("Checking if user {} has product of type {}", userId, productType);
        String sql = "SELECT COUNT(*) FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ? AND p.type = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, productType);
        logger.info("User {} has product of type {}? {}", userId, productType, count != null && count > 0);
        return count != null && count > 0;
    }

    @Cacheable("totalDepositByType")
    public double getTotalDepositByType(String userId, String productType) {
        logger.info("Getting total deposit by type for user {} and product type {}", userId, productType);
        String sql = "SELECT SUM(amount) FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ? AND p.type = ? AND t.type = 'DEPOSIT'";
        Double sum = jdbcTemplate.queryForObject(sql, Double.class, userId, productType);
        logger.info("Total deposit by type for user {} and product type {} is {}", userId, productType, sum);
        return sum == null ? 0 : sum;
    }

    @Cacheable("totalWithdrawalByType")
    public double getTotalWithdrawalByType(String userId, String productType) {
        logger.info("Getting total withdrawal by type for user {} and product type {}", userId, productType);
        String sql = "SELECT SUM(amount) FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ? AND p.type = ? AND t.type = 'WITHDRAW'";
        Double sum = jdbcTemplate.queryForObject(sql, Double.class, userId, productType);
        logger.info("Total withdrawal by type for user {} and product type {} is {}", userId, productType, sum);
        return sum == null ? 0 : sum;
    }

    public RecommendationDTO getById(String productId) {
        return productCatalog.RECOMMENDATIONS.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public boolean isUserActive(String userId, String productType) {
        String sql = "SELECT COUNT(*) FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ? AND p.type = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, productType);
        return count != null && count >= 5;
    }

    public double getTransactionSum(String userId, String productType, String transactionType) {
        String sql = "SELECT SUM(amount) FROM transactions t " +
                "JOIN products p ON t.product_id = p.id " +
                "WHERE t.user_id = ? AND p.type = ? AND t.type = ?";
        Double sum = jdbcTemplate.queryForObject(sql, Double.class, userId, productType, transactionType);
        return sum == null ? 0 : sum;
    }


}