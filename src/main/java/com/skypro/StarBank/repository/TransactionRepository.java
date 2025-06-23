package com.skypro.StarBank.repository;

import com.skypro.StarBank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public class TransactionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Transaction> getTransactionsByUserId(UUID userId) {
        String sql = "SELECT * FROM TRANSACTIONS WHERE USER_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), userId);
    }
}