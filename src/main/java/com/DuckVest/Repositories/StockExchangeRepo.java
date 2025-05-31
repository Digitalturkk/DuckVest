package com.DuckVest.Repositories;

import com.DuckVest.Models.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockExchangeRepo extends JpaRepository<StockExchange, Long> {
}
