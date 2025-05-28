package com.DuckVest.Repositories;

import com.DuckVest.Models.PortfolioStocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStocksRepo extends JpaRepository<PortfolioStocks, Long> {
}
