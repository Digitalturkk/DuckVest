package com.DuckVest.DTOs;

// PortfolioStocksDTO service is in com.DuckVest.Services.PortfolioStocksService
public class PortfolioStocksDTO {
    private Long Id;

    private StockDTO stock;

    private Double quantity;
    private Double averagePrice;
    private Double totalCost; // Total cost of the stock in the portfolio (averagePrice * quantity)

    public PortfolioStocksDTO() {}

    public PortfolioStocksDTO(Long id, Double quantity, Double averagePrice, Double totalCost, StockDTO stock) {
        this.Id = id;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
        this.totalCost = totalCost;
        this.stock = stock;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(StockDTO stock) {
        this.stock = stock;
    }
}
