package com.DuckVest.Services.PortfolioStocksServices;


import com.DuckVest.Models.PortfolioStocks;
import com.DuckVest.Repositories.PortfolioStocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioStocksImplement implements PortfolioStocksService {

    @Autowired
    PortfolioStocksRepo portfolioStocksRepo;

    @Override
    public List<PortfolioStocks> getAllPortfolioStocks() {
        return portfolioStocksRepo.findAll();
    }

    @Override
    public PortfolioStocks getPortfolioStockById(Long id) {
        return portfolioStocksRepo.findById(id).orElse(null);
    }

    @Override
    public void savePortfolioStock(PortfolioStocks portfolioStock) {
        portfolioStocksRepo.save(portfolioStock);
    }

    @Override
    public void deletePortfolioStock(Long id) {
        portfolioStocksRepo.deleteById(id);
    }
}
