package com.task4.command;

import com.task4.service.CartService;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckCartCommand implements Command{

    private final CartService cartService;
    private static final String CART_IS_EMPTY_MESSAGE = "Ваша корзина пуста, саме час щось обрати!)";
    private static final String QUANTITY_MESSAGE = " шт.";

    private static final String TOTAL_PRICE_MESSAGE = "Загальна ціна -> ";
    private static final String PRICE_CURRENCY_MESSAGE = " грн.";

    public CheckCartCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String doCommand() {
        if (cartService.isEmpty()){
            return CART_IS_EMPTY_MESSAGE;
        }
        StringBuilder builder = new StringBuilder();
        AtomicInteger totalPrice = new AtomicInteger(0);
        cartService.getEntrySet().forEach( e -> {
            builder.append(e.getKey().getBookTitle()).append(" ").append(e.getValue()).append(QUANTITY_MESSAGE).append("\n");
            totalPrice.updateAndGet(v1 -> v1 + e.getValue() * e.getKey().getPrice());
        } );
        builder.append(TOTAL_PRICE_MESSAGE).append(totalPrice).append(PRICE_CURRENCY_MESSAGE);
        return builder.toString();
    }
}
