package com.task4.command;

public class CheckCommandsCommand implements Command{

    private static final String COMMAND_MESSAGE = "/catalog - переглянути список доступних книг\n" +
            "/addToCart - додати до корзини \n" +
            "/checkCart - переглянути корзину\n" +
            "/makeOrder - зробити замовлення\n" +
            "/checkLast - переглянути 5 осаніх доданих до корзин\n" +
            "/checkCommands - переглянути команди\n" +
            "/getOrdersByPeriod - знайти замовлення за проміжком часу\n" +
            "/findOrder - знайти замовлення по часу";

    @Override
    public String doCommand() {
        return COMMAND_MESSAGE;
    }
}
