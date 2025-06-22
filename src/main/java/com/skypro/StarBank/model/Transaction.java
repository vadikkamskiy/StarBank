package com.skypro.starbank.model;

public class Transaction {
    private Long id;
    private Long userId;
    private Long productId;
    private double amount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
