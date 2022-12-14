package com.task4.command;

import com.task4.model.Order;
import com.task4.service.OrderService;
import com.task4.utils.OrderDateUtils;


import java.time.LocalDate;
import java.util.List;

public class GetOrdersByPeriodCommand implements Command{
    private final OrderService orderService;

    private static final String ENTER_FIRST_DATE_MESSAGE = "Будь-ласка введіть початкову дату у форматі 'dd/MM/yyyy'!";
    private static final String ENTER_SECOND_DATE_MESSAGE = "Будь-ласка введіть кінцеву дату у форматі 'dd/MM/yyyy'!";
    private static final String WRONG_SECOND_DATE_MESSAGE = "Будьласка, введіть кінцеву дату пізніше за початкову!";
    private static final String NO_ORDERS_BY_DATES_MESSAGE = "Замовлень в обраному періоді часу не знайдено";

    public GetOrdersByPeriodCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String doCommand() {
        LocalDate firstDate = OrderDateUtils.getDate(ENTER_FIRST_DATE_MESSAGE);
        LocalDate secondDate = OrderDateUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
        while (secondDate.isBefore(firstDate)){
            System.out.println(WRONG_SECOND_DATE_MESSAGE);
            secondDate = OrderDateUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
        }
        List<Order> list = orderService.getOrders(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        if(list.isEmpty()){
            return NO_ORDERS_BY_DATES_MESSAGE;
        }
        StringBuilder builder = new StringBuilder();
        list.forEach( e -> builder.append(OrderDateUtils.orderToStringMapper(e)));
        return builder.toString();
    }

}
