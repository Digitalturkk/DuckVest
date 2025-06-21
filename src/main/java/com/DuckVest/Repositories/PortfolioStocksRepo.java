package com.DuckVest.Repositories;

import com.DuckVest.Models.Portfolio;
import com.DuckVest.Models.PortfolioStocks;
import com.DuckVest.Models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioStocksRepo extends JpaRepository<PortfolioStocks, Long> {
    Optional<PortfolioStocks> findByPortfolioAndStock(Portfolio portfolio, Stocks stock);
    List<PortfolioStocks> findAllByPortfolio(Portfolio portfolio);
    PortfolioStocks findPortfolioStocksByPortfolio(Portfolio portfolio);
}
