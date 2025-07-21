// --- service/OrderService.java ---
package com.neha.ecommerce.service;

import com.neha.ecommerce.model.*;
import com.neha.ecommerce.repository.OrderRepository;
import com.neha.ecommerce.repository.ProductRepository;
import com.neha.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    public Order placeOrder(Long userId, List<OrderItem> items) {
        User user = userRepo.findById(userId).orElseThrow();
        Order order = new Order();
        order.setUser(user);

        double total = 0;
        for (OrderItem item : items) {
            Product product = productRepo.findById(item.getProduct().getId()).orElseThrow();
            item.setProduct(product);
            item.setOrder(order);
            item.setPrice(product.getPrice() * item.getQuantity());
            total += item.getPrice();
        }
        order.setTotalAmount(total);
        order.setItems(items);
        return orderRepo.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return orderRepo.findByUser(user);
    }

    public Order getById(Long id) {
        return orderRepo.findById(id).orElseThrow();
    }

    public void cancel(Long id) {
        Order order = orderRepo.findById(id).orElseThrow();
        order.setStatus("CANCELLED");
        orderRepo.save(order);
    }
}