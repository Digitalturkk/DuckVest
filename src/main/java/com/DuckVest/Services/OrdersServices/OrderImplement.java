package com.DuckVest.Services.OrdersServices;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.Exceptions.GlobalNotFound.GlobalNotFoundException;
import com.DuckVest.Models.Orders;
import com.DuckVest.Repositories.OrdersRepo;
import com.DuckVest.Services.InvestorServices.InvestorService;
import com.DuckVest.Services.StockServices.StocksService;
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
    StocksService stocksService;

    @Override
    public List<Orders> getOrders() {
        return ordersRepo.findAll();
    }

    @Override
    public Orders getOrderByID(Long id) {
        return ordersRepo.findById(id).orElseThrow( () -> new GlobalNotFoundException("Orde not found with id: " + id, null));
    }

    @Override
    public void saveOrders(Orders orders) {
        ordersRepo.save(orders);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!ordersRepo.existsById(id)) {
            throw new GlobalNotFoundException("Order not found with id: " + id, null);
        }
        ordersRepo.deleteById(id);
    }

    @Override
    public void deleteAllOrders() {
        if (ordersRepo.count() == 0) {
            throw new GlobalNotFoundException("No orders found to delete", null);
        }
        ordersRepo.deleteAll();
    }

    @Override
    public OrderDTO createOrderDTO(Long orderId, Long investorId, Long stockId) {
        Orders order = ordersRepo.findById(orderId).orElseThrow( () -> new GlobalNotFoundException("Order not found with id: " + orderId, null));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);
        orderDTO.setOrderType(order.getOrderType());
        orderDTO.setInvestorSummaryDTO(investorService.creatInvestorSummaryDTO(investorId));
        orderDTO.setStock(stocksService.createStockDTO(stockId));
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setStockPrice(order.getStockPrice());
        orderDTO.setBrokerFee(order.getBrokerFee());
        orderDTO.setDate(Date.from(Instant.now()));
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setOrderMessage(order.getOrderMessage());
        return orderDTO;
    }
}
