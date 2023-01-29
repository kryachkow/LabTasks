package com.store.command;

import com.store.model.Order;
import com.store.service.OrderService;
import com.store.utils.OrderUtils;
import com.utils.DateTimeConsoleUtils;
import java.time.LocalDate;
import java.util.List;

public class GetOrdersByPeriodCommand implements Command {

  private final OrderService orderService;

  private static final String ENTER_FIRST_DATE_MESSAGE
      = "Будь-ласка введіть початкову дату у форматі 'dd/MM/yyyy'!";
  private static final String ENTER_SECOND_DATE_MESSAGE
      = "Будь-ласка введіть кінцеву дату у форматі 'dd/MM/yyyy'!";
  private static final String WRONG_SECOND_DATE_MESSAGE
      = "Будьласка, введіть кінцеву дату пізніше за початкову!";
  private static final String NO_ORDERS_BY_DATES_MESSAGE
      = "Замовлень в обраному періоді часу не знайдено";

  public GetOrdersByPeriodCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public String doCommand() {
    LocalDate firstDate = DateTimeConsoleUtils.getDate(ENTER_FIRST_DATE_MESSAGE);
    LocalDate secondDate = DateTimeConsoleUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
    while (secondDate.isBefore(firstDate)) {
      System.out.println(WRONG_SECOND_DATE_MESSAGE);
      secondDate = DateTimeConsoleUtils.getDate(ENTER_SECOND_DATE_MESSAGE);
    }
    List<Order> list = orderService.getOrders(firstDate.atStartOfDay(), secondDate.atStartOfDay());
    if (list.isEmpty()) {
      return NO_ORDERS_BY_DATES_MESSAGE;
    }
    StringBuilder builder = new StringBuilder();
    list.forEach(e -> builder.append(OrderUtils.orderToStringMapper(e)));
    return builder.toString();
  }

}
