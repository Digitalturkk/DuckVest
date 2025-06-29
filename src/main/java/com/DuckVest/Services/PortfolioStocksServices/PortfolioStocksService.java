package com.DuckVest.Services.PortfolioStocksServices;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.DTOs.PortfolioStocksDTO;
import com.DuckVest.Models.PortfolioStocks;
import jakarta.mail.MessagingException;

import java.util.List;

public interface PortfolioStocksService {
    List<PortfolioStocks> getAllPortfolioStocks();
    PortfolioStocks getPortfolioStockById(Long id);
    void savePortfolioStock(PortfolioStocks portfolioStock);
    void deletePortfolioStock(Long id);

    void updatePortfolioStock(PortfolioStocks portfolioStock);

    PortfolioStocksDTO createPortfolioStocksDTO(PortfolioStocks portfolioStocks);

    OrderDTO buyStock(Long portfolioId, Long stockId, Double quantity) throws MessagingException;
    OrderDTO sellStock(Long portfolioId, Long stockId, Double quantity) throws MessagingException;
}
