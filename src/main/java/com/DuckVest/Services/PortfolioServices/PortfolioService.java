package com.DuckVest.Services.PortfolioServices;

import com.DuckVest.Models.Portfolio;

import java.util.List;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Portfolio getPortfolioById(Long id);
    void savePortfolio(Portfolio portfolio);
    Double getTotalBalance(Long id);
    Double getReservedBalance(Long id);
}
