package com.DuckVest.Repositories;

import com.DuckVest.Models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestorsRepo extends JpaRepository<Investor, Long> {
    Investor findByEmail(String email);
    Investor findByUsername(String username);
    Investor findById(long id);
    List<Investor> findAllByUsernameOrName(String username, String name);
}
