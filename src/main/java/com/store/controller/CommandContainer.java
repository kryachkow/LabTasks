package com.store.controller;

import com.store.command.AddToCartCommand;
import com.store.command.AddToCatalogCommand;
import com.store.command.CatalogCommand;
import com.store.command.ChangeLocaleCommand;
import com.store.command.CheckCartCommand;
import com.store.command.CheckCommandsCommand;
import com.store.command.CheckLastCommand;
import com.store.command.Command;
import com.store.command.ExitCommand;
import com.store.command.FindOrderByDateCommand;
import com.store.command.GetOrdersByPeriodCommand;
import com.store.command.MakeOrderCommand;
import com.store.context.AppContext;
import com.store.service.CartPartService;
import com.store.service.CartService;
import com.store.service.EditableCatalogService;
import com.store.service.OrderService;
import com.store.service.impl.CartPartServiceImpl;
import com.store.service.impl.CartServiceImpl;
import com.store.service.impl.EditableCatalogServiceImpl;
import com.store.service.impl.OrderServiceImpl;
import com.store.utils.ConstantFields;
import java.util.HashMap;

public class CommandContainer {

  private static final HashMap<String, Command> commands = new HashMap<>();

  static {
    EditableCatalogService editableCatalogService = new EditableCatalogServiceImpl();
    CartPartService cartPartService = new CartPartServiceImpl();
    CartService cartService = new CartServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    commands.put(ConstantFields.CATALOG_COMMAND,
        new CatalogCommand(editableCatalogService));
    commands.put(ConstantFields.ADD_TO_CART_COMMAND,
        new AddToCartCommand(editableCatalogService, cartPartService, cartService));
    commands.put(ConstantFields.CHECK_CART_COMMAND,
        new CheckCartCommand(cartService));
    commands.put(ConstantFields.MAKE_ORDER_COMMAND,
        new MakeOrderCommand(orderService, cartService));
    commands.put(ConstantFields.CHECK_LAST_COMMAND,
        new CheckLastCommand(cartPartService));
    commands.put(ConstantFields.CHECK_COMMANDS_COMMAND,
        new CheckCommandsCommand());
    commands.put(ConstantFields.GET_ORDER_BY_PERIOD_COMMAND,
        new GetOrdersByPeriodCommand(orderService));
    commands.put(ConstantFields.FIND_ORDER_COMMAND,
        new FindOrderByDateCommand(orderService));
    commands.put(ConstantFields.EXIT_COMMAND,
        new ExitCommand(editableCatalogService));
    commands.put(ConstantFields.ADD_TO_CATALOG_COMMAND,
        new AddToCatalogCommand(editableCatalogService, AppContext.getAppContext()
            .getGenerationStrategy()));
    commands.put(ConstantFields.CHANGE_LOCALE_COMMAND,
        new ChangeLocaleCommand());
  }

  public static Command getCommand(String command) {
    return commands.getOrDefault(command, null);
  }
}
