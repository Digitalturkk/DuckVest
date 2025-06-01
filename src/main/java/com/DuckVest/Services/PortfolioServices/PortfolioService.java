package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.Models.Portfolio;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio getPortfolioById(Long id);
    void savePortfolio(Portfolio portfolio);
    void updatePortfolio(Portfolio portfolio);
    void updateTotalBalance(Long id);

    Double getReservedBalance(Long id);

    PortfolioDTO createPortfolioDTO(Long portfolioId, Long investorId, Long portfolioStocksID);
}
