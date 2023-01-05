package com.store.strategy.impl;

import com.store.model.Book;
import com.store.strategy.BookGenerationStrategy;
import com.store.utils.ObjectsFromConsoleUtils;
import java.util.Scanner;


public class ConsoleBookGeneration implements BookGenerationStrategy {

  private static final String ENTER_AUTHOR = "Введіть ім'я автора!";
  private static final String ENTER_TITLE = "Введіть назву книги!";
  private static final String ENTER_PUBLISHER = "Введіть назву видавця!";
  private static final String ENTER_PAGE_NUMBER = "Введіть кількість сторінок!";
  private static final String ENTER_PRICE = "Введіть ціну!";



  @Override
  public Book getBook(long id) {
    String author = ObjectsFromConsoleUtils.getStringFromConsole(ENTER_AUTHOR);
    String title = ObjectsFromConsoleUtils.getStringFromConsole(ENTER_TITLE);
    String publisher = ObjectsFromConsoleUtils.getStringFromConsole(ENTER_PUBLISHER);
    int pageNumber = ObjectsFromConsoleUtils.getIntFromConsole(ENTER_PAGE_NUMBER);
    int price = ObjectsFromConsoleUtils.getIntFromConsole(ENTER_PRICE);

    return new Book(id, author, title, publisher, pageNumber, price);
  }




}
