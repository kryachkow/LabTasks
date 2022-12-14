package com.task4.service.impl;

import com.task4.model.Book;
import com.task4.model.CartPart;
import com.task4.model.Order;
import com.task4.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;


class OrderServiceImplTest {

    OrderService orderService;
    TreeMap<LocalDateTime, Order> orderTreeMap;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        orderService = new OrderServiceImpl();
        Field orderTreeMapField = OrderServiceImpl.class.getDeclaredField("orderTreeMap");
        orderTreeMapField.setAccessible(true);
        orderTreeMap = (TreeMap<LocalDateTime, Order>) orderTreeMapField.get(orderService);

        Order order = new Order(LocalDateTime.of(1999, 11, 22, 22, 22, 22, 22), List.of(new CartPart()));
        Order order2 = new Order(LocalDateTime.of(2002, 1, 22, 22, 22, 22, 22), List.of(new CartPart()));
        Order order3 = new Order(LocalDateTime.of(2005, 1, 22, 22, 22, 22, 22), List.of(new CartPart()));
        orderTreeMap.put(order.getTime(), order);
        orderTreeMap.put(order2.getTime(), order2);
        orderTreeMap.put(order3.getTime(), order3);
    }

    @Test
    void getClosestEntry() {
        Order order = new Order(LocalDateTime.of(1999, 11, 22, 22, 22, 22, 22), List.of(new CartPart()));
        Order order2 = new Order(LocalDateTime.of(2002, 1, 22, 22, 22, 22, 22), List.of(new CartPart()));
        Assertions.assertEquals(order, orderService.getClosestEntry(LocalDateTime.of(1997, 1, 22, 22, 22, 22, 22)));
        Assertions.assertEquals(order2, orderService.getClosestEntry(LocalDateTime.of(2001, 1, 22, 22, 22, 22, 22)));
    }

    @Test
    void getOrders() {
        LocalDateTime firstDate = LocalDateTime.of(1998, 1, 22, 22, 22, 22, 22);
        LocalDateTime secondDate = LocalDateTime.of(2003, 1, 22, 22, 22, 22, 22);
        LocalDateTime thirdDate = LocalDateTime.of(2006, 1, 22, 22, 22, 22, 22);
        Assertions.assertEquals(2, orderService.getOrders(firstDate, secondDate).size());
        Assertions.assertEquals(1, orderService.getOrders(secondDate, thirdDate).size());
        Assertions.assertEquals(3, orderService.getOrders(firstDate, thirdDate).size());
    }


    @Test
    void addOrder() {
        Order order = new Order(LocalDateTime.now(), List.of(new CartPart(), new CartPart()));
        Order order2 = new Order(LocalDateTime.of(1994, 11, 22, 22, 22, 22, 22), List.of(new CartPart(), new CartPart()));
        orderService.addOrder(order.getTime(), order);
        orderService.addOrder(order2.getTime(), order2);
        Assertions.assertEquals(5, orderTreeMap.size());
    }
}