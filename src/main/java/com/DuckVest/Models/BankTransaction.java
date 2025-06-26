package com.DuckVest.Models;

import com.DuckVest.CustomEnums.Currency;
import com.DuckVest.CustomEnums.TransactionStatus;
import com.DuckVest.CustomEnums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bank_transactions")
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "commission")
    private Double commission;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // from who (if it is a transfer)
    @ManyToOne
    @JoinColumn(name = "from_portfolio_id")
    private Portfolio fromPortfolio;

    // to who (if it is a transfer)
    @ManyToOne
    @JoinColumn(name = "to_portfolio_id")
    private Portfolio toPortfolio;



    public BankTransaction() {}

    public BankTransaction(Double amount, TransactionType type, TransactionStatus status,
                           Currency currency, Double commission, String description,
                           Portfolio fromPortfolio, Portfolio toPortfolio) {
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.currency = currency;
        this.commission = commission;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.fromPortfolio = fromPortfolio;
        this.toPortfolio = toPortfolio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Portfolio getFromPortfolio() {
        return fromPortfolio;
    }

    public void setFromPortfolio(Portfolio fromPortfolio) {
        this.fromPortfolio = fromPortfolio;
    }

    public Portfolio getToPortfolio() {
        return toPortfolio;
    }

    public void setToPortfolio(Portfolio toPortfolio) {
        this.toPortfolio = toPortfolio;
    }
}
