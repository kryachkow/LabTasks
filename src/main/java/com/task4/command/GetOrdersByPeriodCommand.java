package com.task4.command;

import com.task4.model.CartPart;
import com.task4.util.OrderDateUtils;
import com.task4.storage.OrderStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class GetOrdersByPeriodCommand implements Command{

    private static final String ENTER_FIRST_DATE_MESSAGE = "Будь-ласка введіть початкову дату у форматі 'dd/MM/yyyy'!";
    private static final String ENTER_SECOND_DATE_MESSAGE = "Будь-ласка введіть кінцеву дату у форматі 'dd/MM/yyyy'!";
    private static final String WRONG_SECOND_DATE_MESSAGE = "Будьласка, введіть кінцеву дату пізніше за початкову!";
    private static final String NO_ORDERS_BY_DATES_MESSAGE = "Замовлень в обраному періоді часу не знайдено";

    @Override
    public String doCommand() {
        LocalDate firstDate = OrderDateUtils.getDate(ENTER_FIRST_DATE_MESSAGE);
        LocalDate secondDate = OrderDateUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
        while (secondDate.isBefore(firstDate)){
            System.out.println(WRONG_SECOND_DATE_MESSAGE);
            secondDate = OrderDateUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
        }
        List< Map.Entry<LocalDateTime, List<CartPart>>> list = OrderStorage.getOrders(firstDate.atStartOfDay(), secondDate.atStartOfDay());
        if(list.isEmpty()){
            return NO_ORDERS_BY_DATES_MESSAGE;
        }
        StringBuilder builder = new StringBuilder();
        list.forEach( e -> builder.append(OrderDateUtils.orderToStringMapper(e)));
        return builder.toString();
    }

}
