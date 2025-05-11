package com.DuckVest.Services.OrdersServices;

import com.DuckVest.Models.Orders;
import com.DuckVest.Repositories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImplement implements OrderService {

    @Autowired
    OrdersRepo ordersRepo;

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
}
