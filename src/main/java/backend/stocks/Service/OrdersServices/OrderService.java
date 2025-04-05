package backend.stocks.Service.OrdersServices;

import backend.stocks.Models.Orders;

import java.util.List;

public interface OrderService {
    void saveOrders(Orders order);
    void deleteOrder(Long id);
    void deleteAllOrders();
    Orders getOrderByID(Long id);
    List<Orders> getOrders();
}
