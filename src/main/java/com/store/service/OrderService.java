package com.store.service;

import com.store.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

  void addOrder(LocalDateTime dateTime, Order order);

  Order getClosestEntry(LocalDateTime dateTime);

  List<Order> getOrders(LocalDateTime firstDate, LocalDateTime secondDate);
}
