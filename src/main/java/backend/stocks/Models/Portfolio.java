package backend.stocks.Models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;
    @OneToOne
    private Investors investor;
    private int totalBalance;
    private int reservedBalance;
    private int availableBalance;
    private Instant lastUpdate = Instant.now();
    @OneToMany
    private List<Stocks> stocksList;
    @OneToMany
    private List<Orders> ordersList;

    public Portfolio(Long portfolioId, Investors investor, int totalBalance, int reservedBalance, int availableBalance, Instant lastUpdate, List<Stocks> stocksList, List<Orders> ordersList) {
        this.portfolioId = portfolioId;
        this.investor = investor;
        this.totalBalance = totalBalance;
        this.reservedBalance = reservedBalance;
        this.availableBalance = availableBalance;
        this.lastUpdate = lastUpdate;
        this.stocksList = stocksList;
        this.ordersList = ordersList;
    }

    public Portfolio() {}

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Investors getInvestor() {
        return investor;
    }

    public void setInvestor(Investors investor) {
        this.investor = investor;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getReservedBalance() {
        return reservedBalance;
    }

    public void setReservedBalance(int reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Stocks> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<Stocks> stocksList) {
        this.stocksList = stocksList;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}