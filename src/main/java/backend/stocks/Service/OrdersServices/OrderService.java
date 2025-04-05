package backend.stocks.Service.OrdersServices;

import backend.stocks.Models.Orders;

import java.util.List;

public interface OrderService {
    void saveOrders(Orders order);
    void deleteOrders(Orders order);
    Orders getOrder(Long id);
    List<Orders> getOrders();
}
