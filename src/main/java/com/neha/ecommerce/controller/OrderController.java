// --- controller/OrderController.java ---
package com.neha.ecommerce.controller;

import com.neha.ecommerce.model.Order;
import com.neha.ecommerce.model.OrderItem;
import com.neha.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public Order place(@PathVariable Long userId, @RequestBody List<OrderItem> items) {
        return orderService.placeOrder(userId, items);
    }

    @GetMapping("/user/{userId}")
    public List<Order> userOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void cancel(@PathVariable Long id) {
        orderService.cancel(id);
    }
}