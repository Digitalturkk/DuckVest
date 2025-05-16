package com.DuckVest.Services.OrdersServices;

import com.DuckVest.CustomEnums.OrderStatus;
import com.DuckVest.CustomEnums.OrderType;
import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.DTOs.PortfolioDTO;
import com.DuckVest.Models.Orders;
import com.DuckVest.Models.Portfolio;

import java.util.List;

public interface OrderService {
    void saveOrders(Orders order);
    void deleteOrder(Long id);
    void deleteAllOrders();
    Orders getOrderByID(Long id);
    List<Orders> getOrders();

    OrderDTO createOrderDTO(Long orderId, Long investorId, Long stockId, int PortfolioID);
}
