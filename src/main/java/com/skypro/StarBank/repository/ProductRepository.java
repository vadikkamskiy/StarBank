package com.skypro.starbank.repository;

import com.skypro.starbank.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("SELECT * FROM PRODUCTS", new BeanPropertyRowMapper<>(Product.class));
    }
}