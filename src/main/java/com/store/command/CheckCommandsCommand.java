package com.store.command;

import com.store.utils.ConstantFields;

public class CheckCommandsCommand implements Command {

  private static final String COMMAND_MESSAGE =
      ConstantFields.CATALOG_COMMAND + " - переглянути список доступних книг\n"
          + ConstantFields.ADD_TO_CART_COMMAND + " - додати до корзини \n"
          + ConstantFields.CHECK_CART_COMMAND + "- переглянути корзину\n"
          + ConstantFields.MAKE_ORDER_COMMAND + " - зробити замовлення\n"
          + ConstantFields.CHECK_LAST_COMMAND + " - переглянути 5 осаніх доданих до корзин\n"
          + ConstantFields.CHECK_COMMANDS_COMMAND + " - переглянути команди\n"
          + ConstantFields.GET_ORDER_BY_PERIOD_COMMAND + " - знайти замовлення за проміжком часу\n"
          + ConstantFields.FIND_ORDER_COMMAND + " - знайти замовлення по часу\n"
          + ConstantFields.EXIT_COMMAND + " - вийти з магазину\n"
          + ConstantFields.ADD_TO_CATALOG_COMMAND + " - додати книгу\n"
          + ConstantFields.CHANGE_LOCALE_COMMAND + " - змінити мову";

  @Override
  public String doCommand() {
    return COMMAND_MESSAGE;
  }
}
