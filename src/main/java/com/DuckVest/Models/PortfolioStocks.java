package com.DuckVest.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "portfolio_stocks")
public class PortfolioStocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is required")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "portfolioId")
    private Portfolio portfolio;
    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stocks stock;

    private Double averagePrice;
    private Double totalCost; // Total cost of the stock in the portfolio (averagePrice * quantity)

    public PortfolioStocks() {}

    public PortfolioStocks(Long id, Double quantity, Double averagePrice, Double totalCost, Portfolio portfolio, Stocks stock) {
        this.id = id;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
        this.totalCost = totalCost;
        this.portfolio = portfolio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
