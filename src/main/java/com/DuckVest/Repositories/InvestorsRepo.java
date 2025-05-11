package com.DuckVest.Repositories;

import com.DuckVest.Models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorsRepo extends JpaRepository<Investor, Long> {
}
