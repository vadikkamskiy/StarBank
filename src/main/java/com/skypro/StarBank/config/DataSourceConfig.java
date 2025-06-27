package com.skypro.StarBank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:./transaction;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;DB_CLOSE_DELAY=-1");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("");
        config.setPassword("");
        config.setReadOnly(true);
        return new HikariDataSource(config);
    }

    @Bean("recommendationsJdbcTemplate")
    public JdbcTemplate recommendationsJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}