package com.DuckVest.DTOs;

import com.DuckVest.Models.StockExchange;

// stockDTO's service is in com.DuckVest.Services.StockServices
public class StockDTO {
    private Long stockID;
    private String companyName;
    private String currency;
    private StockExchangeSummaryDTO stockExchangeSummaryDTO;
    private Double price;
    private Double ask;
    private Double bid;

    public StockDTO() {}

    public StockDTO(Long stockID, String companyName, String currency, StockExchangeSummaryDTO stockExchangeSummaryDTO, Double price, Double ask, Double bid) {
        this.stockID = stockID;
        this.companyName = companyName;
        this.currency = currency;
        this.stockExchangeSummaryDTO = stockExchangeSummaryDTO;
        this.price = price;
        this.ask = ask;
        this.bid = bid;
    }

    public Long getStockID() {
        return stockID;
    }

    public void setStockID(Long stockID) {
        this.stockID = stockID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public StockExchangeSummaryDTO getStockExchangeSummaryDTO() {
        return stockExchangeSummaryDTO;
    }

    public void setStockExchangeSummaryDTO(StockExchangeSummaryDTO stockExchangeSummaryDTO) {
        this.stockExchangeSummaryDTO = stockExchangeSummaryDTO;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }
}
