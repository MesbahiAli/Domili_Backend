package com.example.domily.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.domily.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    
}