package com.task4.command;

import com.task4.storage.CartStorage;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckCartCommand implements Command{

    private static final String CART_IS_EMPTY_MESSAGE = "Ваша корзина пуста, саме час щось обрати!)";
    private static final String QUANTITY_MESSAGE = " шт.";
    private static final String[] TOTAL_PRICE_MESSAGE_PARTS = new String[]{"Загальна ціна -> ", " грн." };
    @Override
    public String doCommand() {
        if (CartStorage.isEmpty()){
            return CART_IS_EMPTY_MESSAGE;
        }
        StringBuilder builder = new StringBuilder();
        AtomicInteger totalPrice = new AtomicInteger(0);
        CartStorage.getEntrySet().forEach( e -> {
            builder.append(e.getKey().getBookTitle()).append(" ").append(e.getValue()).append(QUANTITY_MESSAGE).append("\n");
            totalPrice.updateAndGet(v1 -> v1 + e.getValue() * e.getKey().getPrice());
        } );
        builder.append(TOTAL_PRICE_MESSAGE_PARTS[0]).append(totalPrice).append(TOTAL_PRICE_MESSAGE_PARTS[1]);
        return builder.toString();
    }
}
