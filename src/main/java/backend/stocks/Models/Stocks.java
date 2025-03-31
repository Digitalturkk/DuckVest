package backend.stocks.Models;

import jakarta.persistence.*;

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
    private int price;
    @Column(name = "ask")
    private int ask;
    @Column(name = "bid")
    private int bid;

    public Stocks() {
    }

    public Stocks(Long id, String companyName, String currency, String industry, StockExchange stockExchange, int ask, int price, int bid) {
        this.id = id;
        this.companyName = companyName;
        this.currency = currency;
        this.industry = industry;
        this.stockExchange = stockExchange;
        this.ask = ask;
        this.price = price;
        this.bid = bid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getAsk() {
        return ask;
    }

    public void setAsk(int ask) {
        this.ask = ask;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
}
