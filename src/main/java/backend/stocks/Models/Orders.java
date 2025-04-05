package backend.stocks.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Choose order type")
    private String orderType;
    @ManyToOne
    @NotNull
    private Investors investor;
    @OneToOne
    private Stocks stocks;
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
    @NotBlank
    private String orderStatus;
    @NotNull
    private String orderMessage;
    @NotNull
    private Date date;

    public Orders() {}

    public Orders(Long id, String orderType, Investors investor, Stocks stocks, Double quantity, Double stockPrice, Double brokerFee, Double totalPrice, Boolean isThereExecution, String executionType, String orderStatus, String orderMessage, Date date) {
        this.id = id;
        this.orderType = orderType;
        this.investor = investor;
        this.stocks = stocks;
        this.quantity = quantity;
        this.stockPrice = stockPrice;
        this.brokerFee = brokerFee;
        this.totalPrice = totalPrice;
        this.isThereExecution = isThereExecution;
        this.executionType = executionType;
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

    public @NotBlank(message = "Choose order type") String getOrderType() {
        return orderType;
    }

    public void setOrderType(@NotBlank(message = "Choose order type") String orderType) {
        this.orderType = orderType;
    }

    public @NotNull Investors getInvestor() {
        return investor;
    }

    public void setInvestor(@NotNull Investors investor) {
        this.investor = investor;
    }

    public Stocks getStocks() {
        return stocks;
    }

    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
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

    public @NotBlank String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(@NotBlank String orderStatus) {
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
}
