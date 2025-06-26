package com.skypro.StarBank.repository;

import com.skypro.StarBank.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;




@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT * FROM PRODUCTS", new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> findRecommendations(UUID userId) {
        //TODO
        String sql = "SELECT * FROM PRODUCTS WHERE ID IN (SELECT PRODUCT_ID FROM RECOMMENDATIONS WHERE USER_ID = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), userId);
    }
}