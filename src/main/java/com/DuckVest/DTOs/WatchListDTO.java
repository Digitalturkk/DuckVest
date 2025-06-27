package com.DuckVest.DTOs;

import java.util.Set;

public class WatchListDTO {
    private Set<StockDTO> stocks;

    public WatchListDTO() {
    }

    public WatchListDTO(Set<StockDTO> stocks) {
        this.stocks = stocks;
    }

    public Set<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(Set<StockDTO> stocks) {
        this.stocks = stocks;
    }
}
