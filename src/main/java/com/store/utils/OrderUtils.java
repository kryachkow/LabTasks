package com.store.utils;

import com.store.model.Order;


public class OrderUtils {

  private static final String ORDER_BY_DATE_MESSAGE = "Замовлення за датою ";
  private static final String BOOKS_MESSAGE = "Книжки : \n";
  private static final String IN_QUANTITY_MESSAGE = " у кількості ";
  private static final String QT_MESSAGE = " шт. \n";
  private static final String SEPARATOR_MESSAGE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n";


  private OrderUtils() {
  }

  public static String orderToStringMapper(Order order) {
    StringBuilder builder = new StringBuilder();
    builder.append(ORDER_BY_DATE_MESSAGE)
        .append(order.getTime())
        .append("\n")
        .append(BOOKS_MESSAGE);
    order.getCart()
        .forEach(cartPart -> builder.append(BookUtils.bookToCustomerString(cartPart.getBook()))
            .append(IN_QUANTITY_MESSAGE)
            .append(cartPart.getQuantity())
            .append(QT_MESSAGE));
    builder.append(SEPARATOR_MESSAGE);
    return builder.toString();
  }


}
