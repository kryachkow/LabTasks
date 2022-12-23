package com.store.strategy.impl;

import com.store.model.Book;
import com.store.strategy.BookGenerationStrategy;
import java.util.Scanner;

public class ConsoleBookGeneration implements BookGenerationStrategy {

  private static final String ENTER_AUTHOR = "Введіть ім'я автора!";
  private static final String ENTER_TITLE = "Введіть назву книги!";
  private static final String ENTER_PUBLISHER = "Введіть назву видавця!";
  private static final String ENTER_PAGE_NUMBER = "Введіть кількість сторінок!";
  private static final String ENTER_PRICE = "Введіть ціну!";


  private final Scanner scanner = new Scanner(System.in);

  @Override
  public Book getBook(long id) {
    String author = getStringFromConsole(ENTER_AUTHOR);
    String title = getStringFromConsole(ENTER_TITLE);
    String publisher = getStringFromConsole(ENTER_PUBLISHER);
    int pageNumber = getIntFromConsole(ENTER_PAGE_NUMBER);
    int price = getIntFromConsole(ENTER_PRICE);

    return new Book(id, author, title, publisher, pageNumber, price);
  }

  private String getStringFromConsole(String message) {
    String toRet = null;
    while (toRet == null || toRet.trim().isEmpty()) {
      System.out.println(message);
      toRet = scanner.nextLine();
    }
    return toRet;
  }

  private int getIntFromConsole(String message) {
    int toRet = -1;
    while (toRet < 0) {
      System.out.println(message);
      toRet = scanner.nextInt();
    }
    return toRet;
  }
}
