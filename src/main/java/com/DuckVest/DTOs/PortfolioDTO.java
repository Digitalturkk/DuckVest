package com.DuckVest.DTOs;

import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Stocks;

import java.time.Instant;
import java.util.List;

// DTOs service is in com.DuckVest.Services.PortfolioService
public class PortfolioDTO {

    private Long portfolioId;
    private String investorName;
    private Double totalBalance;
    private Double reservedBalance;
    private Double availableBalance;
    private Instant lastUpdate;
    private List<Stocks> stocksList;
    private List<Orders> ordersList;

    public PortfolioDTO(Long portfolioId, String investorName, Double totalBalance, Double reservedBalance, Double availableBalance, Instant lastUpdate, List<Stocks> stocksList, List<Orders> ordersList) {
        this.portfolioId = portfolioId;
        this.investorName = investorName;
        this.totalBalance = totalBalance;
        this.reservedBalance = reservedBalance;
        this.availableBalance = availableBalance;
        this.lastUpdate = lastUpdate;
        this.stocksList = stocksList;
        this.ordersList = ordersList;
    }

    public PortfolioDTO() {
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public List<Stocks> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<Stocks> stocksList) {
        this.stocksList = stocksList;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Double getReservedBalance() {
        return reservedBalance;
    }

    public void setReservedBalance(Double reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }
}
