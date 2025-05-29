package com.DuckVest.Services.PortfolioStocksServices;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.Models.PortfolioStocks;

import java.util.List;

public interface PortfolioStocksService {
    List<PortfolioStocks> getAllPortfolioStocks();
    PortfolioStocks getPortfolioStockById(Long id);
    void savePortfolioStock(PortfolioStocks portfolioStock);
    void deletePortfolioStock(Long id);

    void updatePortfolioStock(PortfolioStocks portfolioStock);

    OrderDTO buyStock(Long portfolioId, Long stockId, Double quantity, Double brokerFee);
    OrderDTO sellStock(Long portfolioId, Long stockId, Double quantity, Double brokerFee);
}
