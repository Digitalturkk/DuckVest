package com.DuckVest.Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "watchlists")
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;
    @ManyToMany
    @JoinTable(
            name = "watchlist_stocks",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private Set<Stocks> stocks;

    public WatchList() {
    }

    public WatchList(Long id, Investor investor, Set<Stocks> stocks) {
        this.id = id;
        this.investor = investor;
        this.stocks = stocks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Set<Stocks> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stocks> stocks) {
        this.stocks = stocks;
    }
}
