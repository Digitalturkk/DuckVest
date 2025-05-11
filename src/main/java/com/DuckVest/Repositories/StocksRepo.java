package com.DuckVest.Repositories;

import com.DuckVest.Models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepo  extends JpaRepository<Stocks, Long> {
}

