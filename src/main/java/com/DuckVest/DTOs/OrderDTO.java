package com.DuckVest.DTOs;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.CustomEnums.OrderType;
import com.DuckVest.DTOs.InvestorDTOs.InvestorSummaryDTO;

import java.util.Date;

// OrderDTO's service is in com.DuckVest.Services.OrderService
public class OrderDTO {
    private Long id;
    private OrderType orderType;
    private InvestorSummaryDTO investorSummaryDTO;
    private StockDTO stock;
    private Double quantity;
    private Double stockPrice;
    private Double brokerFee;
    private OrderStatus orderStatus;
    private String orderMessage;
    private Date date;

    public OrderDTO() {}

    public OrderDTO(Long id, OrderType orderType, InvestorSummaryDTO investorSummaryDTO, StockDTO stock, Double quantity, Double stockPrice, Double brokerFee, OrderStatus orderStatus, String orderMessage, Date date) {
        this.id = id;
        this.orderType = orderType;
        this.investorSummaryDTO = investorSummaryDTO;
        this.stock = stock;
        this.quantity = quantity;
        this.stockPrice = stockPrice;
        this.brokerFee = brokerFee;
        this.orderStatus = orderStatus;
        this.orderMessage = orderMessage;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public InvestorSummaryDTO getInvestorSummaryDTO() {
        return investorSummaryDTO;
    }

    public void setInvestorSummaryDTO(InvestorSummaryDTO investorSummaryDTO) {
        this.investorSummaryDTO = investorSummaryDTO;
    }

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(StockDTO stock) {
        this.stock = stock;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Double getBrokerFee() {
        return brokerFee;
    }

    public void setBrokerFee(Double brokerFee) {
        this.brokerFee = brokerFee;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
