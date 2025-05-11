package com.DuckVest.Repositories;

import com.DuckVest.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
}
