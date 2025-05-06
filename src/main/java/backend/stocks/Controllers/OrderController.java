package backend.stocks.Controllers;

import backend.stocks.Models.Orders;
import backend.stocks.Services.OrdersServices.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @GetMapping("/get-all")
    public List<Orders> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/get-id={id}")
    public Orders getOrderById(@PathVariable Long id) {
        return orderService.getOrderByID(id);
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Orders order) {
        orderService.saveOrders(order);
    }

    @DeleteMapping("/delete-all")
    public void deleteAllOrders() {
        orderService.deleteAllOrders();
    }

    @DeleteMapping("/delete-id={id}")
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
