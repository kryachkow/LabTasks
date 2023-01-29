package com.store.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ObjectsFromConsoleUtils {

  private static final String ENTER_A_NUMBER = "Введіть число!";
  private static final Scanner scanner = new Scanner(System.in);

  private ObjectsFromConsoleUtils() {
  }

  public static String getStringFromConsole(String message) {
    String toRet = null;
    while (toRet == null || toRet.trim().isEmpty()) {
      System.out.println(message);
      toRet = scanner.nextLine();
    }
    return toRet;
  }

  public static int getIntFromConsole(String message) {
    int toRet = -1;
    do {
      System.out.println(message);
      try {
        toRet = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println(ENTER_A_NUMBER);
      }
    } while (toRet < 0);
    return toRet;
  }

}
