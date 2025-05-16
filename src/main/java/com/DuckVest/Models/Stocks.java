package com.DuckVest.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "stocks")
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "currency")
    private String currency;
    @Column(name = "industry")
    private String industry;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "stock_exchange_id", referencedColumnName = "id")
    private StockExchange stockExchange;
    @Column(name = "price")
    private Double price;
    @Column(name = "ask")
    private Double ask;
    @Column(name = "bid")
    private Double bid;
    @ManyToMany
    private List<Portfolio> portfolios;
    @OneToMany(mappedBy = "stock", cascade = CascadeType.PERSIST)
    private List<Orders> orders;

    public Stocks() {
    }

    public Stocks(Long id, String companyName, String currency, String industry, StockExchange stockExchange, Double ask, Double price, Double bid, List<Portfolio> portfolios, List<Orders> orders) {
        this.id = id;
        this.companyName = companyName;
        this.currency = currency;
        this.industry = industry;
        this.stockExchange = stockExchange;
        this.ask = ask;
        this.price = price;
        this.bid = bid;
        this.portfolios = portfolios;
        this.orders = orders;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Portfolio> getPortfolio() {
        return portfolios;
    }

    public void setPortfolio(List<Portfolio> portfolio) {
        this.portfolios = portfolio;
    }
}
