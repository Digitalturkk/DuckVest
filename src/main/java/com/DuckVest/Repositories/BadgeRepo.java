package com.DuckVest.Repositories;

import com.DuckVest.Models.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepo extends JpaRepository<Badge, Long> {
}
