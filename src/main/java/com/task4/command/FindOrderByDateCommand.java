package com.task4.command;

import com.task4.model.Order;
import com.task4.service.OrderService;
import com.task4.utils.OrderDateUtils;

import java.time.LocalDate;

public class FindOrderByDateCommand implements Command{
    private final OrderService orderService;

    private static final String ENTER_SEARCH_DATE_MESSAGE = "Будь-ласка введіть дату пошуку у форматі 'dd/MM/yyyy'!";
    private static final String LIST_OF_ORDERS_IS_EMPTY_MESSAGE = "Наразі список замовлень пустий!";

    public FindOrderByDateCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String doCommand() {
        LocalDate date = OrderDateUtils.getDate(ENTER_SEARCH_DATE_MESSAGE);
        Order closestEntry = orderService.getClosestEntry(date.atStartOfDay());
        if(closestEntry == null){
            return LIST_OF_ORDERS_IS_EMPTY_MESSAGE;
        }
        return OrderDateUtils.orderToStringMapper(closestEntry);
    }
}
