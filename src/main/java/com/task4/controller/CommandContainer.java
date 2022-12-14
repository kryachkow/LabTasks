package com.task4.controller;

import com.task4.command.*;
import com.task4.service.CatalogService;
import com.task4.service.CartPartService;
import com.task4.service.CartService;
import com.task4.service.OrderService;
import com.task4.service.impl.CatalogServiceImpl;
import com.task4.service.impl.CartPartServiceImpl;
import com.task4.service.impl.CartServiceImpl;
import com.task4.service.impl.OrderServiceImpl;
import com.task4.utils.ConstantFields;

import java.util.HashMap;

public class CommandContainer {
    private static final HashMap<String, Command> commands = new HashMap<>();
    static {
        CatalogService catalogService = new CatalogServiceImpl();
        CartPartService cartPartService = new CartPartServiceImpl();
        CartService cartService = new CartServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        commands.put(ConstantFields.CATALOG_COMMAND, new CatalogCommand(catalogService));
        commands.put(ConstantFields.ADD_TO_CART_COMMAND, new AddToCartCommand(catalogService, cartPartService, cartService));
        commands.put(ConstantFields.CHECK_CART_COMMAND, new CheckCartCommand(cartService));
        commands.put(ConstantFields.MAKE_ORDER_COMMAND, new MakeOrderCommand(orderService, cartService));
        commands.put(ConstantFields.CHECK_LAST_COMMAND, new CheckLastCommand(cartPartService));
        commands.put(ConstantFields.CHECK_COMMANDS_COMMAND, new CheckCommandsCommand());
        commands.put(ConstantFields.GET_ORDER_BY_PERIOD_COMMAND, new GetOrdersByPeriodCommand(orderService));
        commands.put(ConstantFields.FIND_ORDER_COMMAND, new FindOrderByDateCommand(orderService));
    }

    public static Command getCommand(String command){
        return commands.getOrDefault(command, null);
    }
}
