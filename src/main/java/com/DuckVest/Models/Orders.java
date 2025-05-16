package com.DuckVest.Models;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.CustomEnums.OrderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Choose order type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    @ManyToOne
    @NotNull
    private Investor investor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_id")
    @NotNull
    private Stocks stock;
    @NotNull
    private Double quantity;
    @NotNull
    private Double stockPrice;
    @NotNull
    private Double brokerFee;
    @NotNull
    private Double totalPrice;
    @NotNull
    private Boolean isThereExecution;

    private String executionType; // Will be null if isThereExecution gets false
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @NotNull
    private String orderMessage;
    @NotNull
    private Date date;
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public String getExecutionTypeChecked() {
        return Boolean.FALSE.equals(isThereExecution) ? null : executionType;
    }

    public Orders() {}

    public Orders(Long id, OrderType orderType, Investor investor, Stocks stock, Double quantity, Double stockPrice, Double brokerFee, Double totalPrice, Boolean isThereExecution, String executionType, OrderStatus orderStatus, String orderMessage, Date date, Portfolio portfolio) {
        this.id = id;
        this.orderType = orderType;
        this.investor = investor;
        this.stock = stock;
        this.quantity = quantity;
        this.stockPrice = stockPrice;
        this.brokerFee = brokerFee;
        this.totalPrice = totalPrice;
        this.isThereExecution = isThereExecution;
        this.executionType = executionType;
        this.orderStatus = orderStatus;
        this.orderMessage = orderMessage;
        this.date = date;
        this.portfolio = portfolio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Choose order type") OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(@NotNull(message = "Choose order type") OrderType orderType) {
        this.orderType = orderType;
    }

    public @NotNull Investor getInvestor() {
        return investor;
    }

    public void setInvestor(@NotNull Investor investor) {
        this.investor = investor;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }

    public @NotNull Double getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull Double quantity) {
        this.quantity = quantity;
    }

    public @NotNull Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(@NotNull Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public @NotNull Double getBrokerFee() {
        return brokerFee;
    }

    public void setBrokerFee(@NotNull Double brokerFee) {
        this.brokerFee = brokerFee;
    }

    public @NotNull Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public @NotNull Boolean getThereExecution() {
        return isThereExecution;
    }

    public void setThereExecution(@NotNull Boolean thereExecution) {
        isThereExecution = thereExecution;
    }

    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    public @NotNull OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(@NotNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public @NotNull String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(@NotNull String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public @NotNull Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
