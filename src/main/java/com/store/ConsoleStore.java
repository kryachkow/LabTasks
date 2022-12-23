package com.store;

import com.store.controller.Controller;
import com.store.utils.ConstantFields;
import java.util.Scanner;

public class ConsoleStore {

  private static final String WELCOME_MESSAGE = "Вітаю в книжковому магазині";

  public static void main(String[] args) {
    Controller controller = new Controller();
    System.out.println(WELCOME_MESSAGE);
    System.out.println(controller.doCommand(ConstantFields.CHECK_COMMANDS_COMMAND));
    Scanner consoleStore = new Scanner(System.in);

    while (true) {
      String commandName = consoleStore.nextLine().trim();
      System.out.println(controller.doCommand(commandName));
    }
  }


}
