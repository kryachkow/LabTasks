package com.task4.service;

import com.task4.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void addOrder(LocalDateTime dateTime, Order order);

     Order getClosestEntry(LocalDateTime dateTime);

     List<Order> getOrders(LocalDateTime firstDate, LocalDateTime secondDate);
}
