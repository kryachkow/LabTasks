package com.store;

import com.store.context.AppContext;
import com.store.controller.Controller;
import com.store.service.impl.BookGeneratingStrategyServiceImpl;
import com.store.utils.ConstantFields;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleStore {

  private static final String WELCOME_MESSAGE = "Вітаю в книжковому магазині";

  public static void main(String[] args) {
    System.out.println(WELCOME_MESSAGE);
    AppContext appContext = AppContext.getAppContext();
    appContext.setGenerationStrategy(new BookGeneratingStrategyServiceImpl().getStrategy());
    appContext.setLocale(new Locale("uk"));
    Controller controller = new Controller();
    System.out.println(controller.doCommand(ConstantFields.CHECK_COMMANDS_COMMAND));
    Scanner consoleStore = new Scanner(System.in);

    while (true) {
      String commandName = consoleStore.nextLine().trim();
      System.out.println(controller.doCommand(commandName));
    }
  }


}
