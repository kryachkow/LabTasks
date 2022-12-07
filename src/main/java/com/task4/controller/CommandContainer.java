package com.task4.controller;

import com.task4.command.*;

import java.util.HashMap;

public class CommandContainer {
    private static final HashMap<String, Command> commands = new HashMap<>();
    static {
        commands.put("/catalog", new CatalogCommand());
        commands.put("/addToCart", new AddToCartCommand());
        commands.put("/checkCart", new CheckCartCommand());
        commands.put("/makeOrder", new MakeOrderCommand());
        commands.put("/checkLast", new CheckLastCommand());
        commands.put("/checkCommands", new CheckCommandsCommand());
        commands.put("/getOrdersByPeriod", new GetOrdersByPeriodCommand());
        commands.put("/findOrder", new FindOrderByDateCommand());
    }

    public static Command getCommand(String command){
        return commands.getOrDefault(command, null);
    }
}
