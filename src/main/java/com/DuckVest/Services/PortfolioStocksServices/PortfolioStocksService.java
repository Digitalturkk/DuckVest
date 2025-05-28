package com.DuckVest.Services.PortfolioStocksServices;

import com.DuckVest.Models.PortfolioStocks;

import java.util.List;

public interface PortfolioStocksService {
    List<PortfolioStocks> getAllPortfolioStocks();
    PortfolioStocks getPortfolioStockById(Long id);
    void savePortfolioStock(PortfolioStocks portfolioStock);
    void deletePortfolioStock(Long id);
}
