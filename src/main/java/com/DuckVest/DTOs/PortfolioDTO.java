package com.DuckVest.DTOs;

import com.DuckVest.Models.Orders;

import java.util.List;

// portfolioDTOs service is in com.DuckVest.Services.PortfolioService
public class PortfolioDTO {

    private Long portfolioId;
    private String investorName;
    private Double totalBalance;
    private Double reservedBalance;
    private Double availableBalance;
    private List<StockDTO> stocksList;
    private List<OrderDTO> ordersList;

    public PortfolioDTO(Long portfolioId, String investorName, Double totalBalance, Double reservedBalance, Double availableBalance, List<StockDTO> stocksList, List<OrderDTO> ordersList) {
        this.portfolioId = portfolioId;
        this.investorName = investorName;
        this.totalBalance = totalBalance;
        this.reservedBalance = reservedBalance;
        this.availableBalance = availableBalance;
        this.stocksList = stocksList;
        this.ordersList = ordersList;
    }

    public PortfolioDTO() {
    }

    public List<OrderDTO> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrderDTO> ordersList) {
        this.ordersList = ordersList;
    }

    public List<StockDTO> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<StockDTO> stocksList) {
        this.stocksList = stocksList;
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
