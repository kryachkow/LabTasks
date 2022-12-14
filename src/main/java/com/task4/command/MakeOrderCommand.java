package com.task4.command;

import com.task4.model.CartPart;
import com.task4.model.Order;
import com.task4.service.CartService;
import com.task4.service.OrderService;
import com.task4.utils.OrderDateUtils;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;


public class MakeOrderCommand implements Command{

    private final OrderService orderService;
    private final CartService cartService;
    private static final String EMPTY_CART_MESSAGE = "Ваша корзина пуста, саме час щось обрати!)";
    private static final String ORDER_WAS_MADE_MESSAGE = "Замовлення зроблено!";

    public MakeOrderCommand(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @Override
    public String doCommand() {
        if (cartService.isEmpty()){
            return EMPTY_CART_MESSAGE;
        }
        LocalDateTime dateTime = OrderDateUtils.getDateTime();

        List<CartPart> cart = cartService.getEntrySet()
                .stream()
                .map(e -> new CartPart(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        orderService.addOrder(dateTime, new Order(dateTime, cart));
        cartService.clear();

        return ORDER_WAS_MADE_MESSAGE;
    }
}
