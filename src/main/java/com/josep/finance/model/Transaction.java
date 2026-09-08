package com.josep.finance.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private String type;
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate date;

    public Transaction(
            String type,
            BigDecimal amount,
            String category,
            String description,
            LocalDate date
    ) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}