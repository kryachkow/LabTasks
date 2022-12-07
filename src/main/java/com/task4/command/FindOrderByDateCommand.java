package com.task4.command;

import com.task4.model.CartPart;
import com.task4.util.OrderDateUtils;
import com.task4.storage.OrderStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FindOrderByDateCommand implements Command{

    private static final String ENTER_SEARCH_DATE_MESSAGE = "Будь-ласка введіть дату пошуку у форматі 'dd/MM/yyyy'!";
    private static final String LIST_OF_ORDERS_IS_EMPTY_MESSAGE = "Наразі список замовлень пустий!";
    @Override
    public String doCommand() {
        LocalDate date = OrderDateUtils.getDate(ENTER_SEARCH_DATE_MESSAGE);
        Map.Entry<LocalDateTime, List<CartPart>> closestEntry = OrderStorage.getClosestEntry(date.atStartOfDay());
        if(closestEntry == null){
            return LIST_OF_ORDERS_IS_EMPTY_MESSAGE;
        }
        return OrderDateUtils.orderToStringMapper(closestEntry);
    }
}
