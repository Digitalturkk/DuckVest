package com.DuckVest.Services.OrdersServices;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.Models.Orders;
import com.DuckVest.Repositories.OrdersRepo;
import com.DuckVest.Services.InvestorServices.InvestorService;
import com.DuckVest.Services.StockServices.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class OrderImplement implements OrderService {

    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    InvestorService investorService;
    @Autowired
    @Lazy
    StockService stockService;

    @Override
    public List<Orders> getOrders() {
        return ordersRepo.findAll();
    }

    @Override
    public Orders getOrderByID(Long id) {
        return ordersRepo.findById(id).get();
    }

    @Override
    public void saveOrders(Orders orders) {
        ordersRepo.save(orders);
    }

    @Override
    public void deleteOrder(Long id) {
        ordersRepo.deleteById(id);
    }

    @Override
    public void deleteAllOrders() {
        ordersRepo.deleteAll();
    }

    @Override
    public OrderDTO createOrderDTO(Long orderId, Long investorId, Long stockId, int portfolioId) {
        Orders order = ordersRepo.findById(orderId).get();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);
        orderDTO.setOrderType(order.getOrderType());
        orderDTO.setInvestor(investorService.createInvestorDTO(investorId, portfolioId));
        orderDTO.setStock(stockService.createStockDTO(stockId));
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setStockPrice(order.getStockPrice());
        orderDTO.setBrokerFee(order.getBrokerFee());
        orderDTO.setDate(Date.from(Instant.now()));
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setOrderMessage(orderDTO.getOrderMessage());
        return orderDTO;
    }
}
