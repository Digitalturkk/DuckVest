package com.DuckVest.Repositories;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
    List<Orders> findOrdersByPortfolioIdAndOrderStatus(Long portfolioId, OrderStatus orderStatus);
}
