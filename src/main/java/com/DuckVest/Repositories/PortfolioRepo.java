package com.DuckVest.Repositories;

import com.DuckVest.Models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
}
