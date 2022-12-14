package com.task4.service.impl;

import com.task4.model.Order;
import com.task4.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final TreeMap<LocalDateTime, Order> orderTreeMap = new TreeMap<>();

    public void addOrder(LocalDateTime dateTime, Order order){
        orderTreeMap.put(dateTime, order);
    }

    public Order getClosestEntry(LocalDateTime dateTime){
        if(orderTreeMap.isEmpty()){
            return null;
        }
        Map.Entry<LocalDateTime, Order> lowerEntry = orderTreeMap.lowerEntry(dateTime);
        Map.Entry<LocalDateTime, Order> higherEntry = orderTreeMap.higherEntry(dateTime);
        if(lowerEntry == null){
            return higherEntry.getValue();
        }
        if(higherEntry == null){
            return lowerEntry.getValue();
        }
        int diffLower = dateTime.compareTo(lowerEntry.getKey());
        int diffHigher = dateTime.compareTo(higherEntry.getKey());

        if( diffLower + diffHigher > 0){
            return higherEntry.getValue();
        }
        return lowerEntry.getValue();
    }

    public List<Order> getOrders(LocalDateTime firstDate, LocalDateTime secondDate){
        return orderTreeMap
                .entrySet()
                .stream()
                .filter( e-> e.getKey().isBefore(secondDate) && e.getKey().isAfter(firstDate))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
