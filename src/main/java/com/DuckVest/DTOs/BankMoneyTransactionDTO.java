package com.DuckVest.DTOs;

import com.DuckVest.CustomEnums.Currency;
import com.DuckVest.CustomEnums.TransactionType;

public class BankMoneyTransactionDTO {
    private Double amount;
    private TransactionType type;
    private Currency currency;
    private Double commission;
    private String description;

    public BankMoneyTransactionDTO() {}

    public BankMoneyTransactionDTO(Double amount, TransactionType type, Currency currency, Double commission, String description) {
        this.amount = amount;
        this.type = type;
        this.currency = currency;
        this.commission = commission;
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
