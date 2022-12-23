package com.store.command;

import com.store.model.CartPart;
import com.store.service.CartPartService;
import java.util.List;
import java.util.Map;

public class CheckLastCommand implements Command {

  private final CartPartService cartPartService;
  private static final String ID_NUMBER_MESSAGE = "Порядковий номер додавання ";
  private static final String BOOK_MESSAGE = " | Книга ";
  private static final String IN_QUANTITY_MESSAGE = " | у кількості ";

  public CheckLastCommand(CartPartService cartPartService) {
    this.cartPartService = cartPartService;
  }

  @Override
  public String doCommand() {
    List<Map.Entry<Long, CartPart>> list = cartPartService.getFiveLastEntries();
    StringBuilder builder = new StringBuilder();
    list.forEach(e -> builder.append(ID_NUMBER_MESSAGE)
        .append(e.getKey())
        .append(BOOK_MESSAGE)
        .append(e.getValue().getBook().getBookTitle())
        .append(IN_QUANTITY_MESSAGE)
        .append(e.getValue().getQuantity())
        .append("\n"));
    return builder.toString();
  }
}
