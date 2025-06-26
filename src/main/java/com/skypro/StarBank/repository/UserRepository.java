package com.skypro.StarBank.repository;

import com.skypro.StarBank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException{
            User user = new User();
            user.setId(UUID.fromString(rs.getString("ID")));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            return user;
        }
    };

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USERS", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(UUID userId){
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, ROW_MAPPER, userId.toString());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
