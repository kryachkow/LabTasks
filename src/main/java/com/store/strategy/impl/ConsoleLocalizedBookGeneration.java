package com.store.strategy.impl;

import com.store.model.Book;
import com.store.service.LocalizationService;
import com.store.strategy.BookGenerationStrategy;
import com.store.utils.ConstantFields;
import com.store.utils.ObjectsFromConsoleUtils;
import java.util.Map;

public class ConsoleLocalizedBookGeneration implements BookGenerationStrategy {

  private final LocalizationService bookLocalizationService;
  private static final String ENTER = "Введіть ";

  public ConsoleLocalizedBookGeneration(LocalizationService bookLocalizationService) {
    this.bookLocalizationService = bookLocalizationService;
  }

  @Override
  public Book getBook(long id) {
    Map<String, String> fieldMap = bookLocalizationService.getLocalizedFields();
    String author = ObjectsFromConsoleUtils.getStringFromConsole(ENTER
        + fieldMap.get(ConstantFields.AUTHOR_FIELD));
    String title = ObjectsFromConsoleUtils.getStringFromConsole(ENTER
        + fieldMap.get(ConstantFields.BOOK_TITLE_FIELD));
    String publisher = ObjectsFromConsoleUtils.getStringFromConsole(ENTER
        + fieldMap.get(ConstantFields.PUBLISHER_FIELD));
    int pageNumber = ObjectsFromConsoleUtils.getIntFromConsole(
        ENTER + fieldMap.get(ConstantFields.PAGE_NUMBER_FIELD));
    int price = ObjectsFromConsoleUtils.getIntFromConsole(
        ENTER + fieldMap.get(ConstantFields.PRICE_FIELD));

    return new Book(id, author, title, publisher, pageNumber, price);
  }
}
