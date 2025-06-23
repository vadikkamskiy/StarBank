package com.skypro.StarBank.model;

import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private double amount;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getProductId() { return productId; }
    public void setProductId(UUID productId) { this.productId = productId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
