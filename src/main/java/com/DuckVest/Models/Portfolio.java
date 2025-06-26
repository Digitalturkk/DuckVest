package com.DuckVest.Models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;

    @OneToOne(cascade = CascadeType.ALL)
    private Investor investor;

    private Double totalBalance;
    private Double reservedBalance;
    private Double availableBalance;
    private Instant lastUpdate;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.PERSIST )
    private List<Orders> ordersList;
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.PERSIST)
    private List<PortfolioStocks> portfolioStocks;

    public Portfolio(Long portfolioId, Investor investor, Double totalBalance, Double reservedBalance, Double availableBalance, Instant lastUpdate, List<Orders> ordersList, List<PortfolioStocks> portfolioStocks) {
        this.portfolioId = portfolioId;
        this.investor = investor;
        this.totalBalance = totalBalance;
        this.reservedBalance = reservedBalance;
        this.availableBalance = availableBalance;
        this.lastUpdate = lastUpdate;
        this.ordersList = ordersList;
        this.portfolioStocks = portfolioStocks;
    }

    public Portfolio() {
    }

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdate = Instant.now();
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Double getReservedBalance() {
        return reservedBalance;
    }

    public void setReservedBalance(Double reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public List<PortfolioStocks> getPortfolioStocks() {
        return portfolioStocks;
    }

    public void setPortfolioStocks(List<PortfolioStocks> portfolioStocks) {
        this.portfolioStocks = portfolioStocks;
    }
}