package com.example.domily.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domily.entity.HomeService;
import com.example.domily.entity.Order;
import com.example.domily.entity.OrderRequest;
import com.example.domily.entity.User;
import com.example.domily.repository.OrderRepository;
import com.example.domily.repository.ServiceRepository;
import com.example.domily.repository.UserRepository;
import com.example.domily.service.OrderService;



@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository homeServiceRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        // Fetch the User (client) and HomeService (service) from the database
        User user = userRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        HomeService service = homeServiceRepository.findById(orderRequest.getServiceId())
                .orElseThrow(() -> new RuntimeException("HomeService not found"));

        // Create a new Order and set the fields
        Order order = new Order();
        order.setOrdre_adresse(orderRequest.getOrdre_adresse());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setStatus(orderRequest.getStatus());
        order.setStart_hour(orderRequest.getStart_hour());
        order.setEnd_hour(orderRequest.getEnd_hour());
        order.setUser(user); // Set the User (client)
        order.setService(service); // Set the HomeService

        // Save the Order to the database
        return orderRepository.save(order);
    }

   

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.findOrdersByUserId(userId);
    }

}