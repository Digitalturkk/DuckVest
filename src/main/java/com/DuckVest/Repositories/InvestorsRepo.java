package com.DuckVest.Repositories;

import com.DuckVest.Models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestorsRepo extends JpaRepository<Investor, Long> {
    Investor findByEmail(String email);
    Investor findByUsername(String username);
    Investor findById(long id);
    @Query("SELECT i FROM Investor i WHERE LOWER(i.username) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Investor> findByUsernameContainingIgnoreCaseOrNameContainingIgnoreCase(String username, String name);
}
