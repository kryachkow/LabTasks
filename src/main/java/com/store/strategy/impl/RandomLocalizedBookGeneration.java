package com.store.strategy.impl;

import com.store.model.Book;
import com.store.service.LocalizationService;
import com.store.service.impl.BookLocalizationServiceImpl;
import com.store.strategy.BookGenerationStrategy;
import com.store.utils.ConstantFields;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public class RandomLocalizedBookGeneration implements BookGenerationStrategy {

  private final LocalizationService bookLocalizationService;

  public RandomLocalizedBookGeneration(LocalizationService bookLocalizationService) {
    this.bookLocalizationService = bookLocalizationService;
  }

  @Override
  public Book getBook(long id) {
    Random random = new Random();
    Map<String, String> fieldsNames = bookLocalizationService.getLocalizedFields();
    String author = fieldsNames.get(ConstantFields.AUTHOR_FIELD) + " " + random.nextInt(1000);
    String title = fieldsNames.get(ConstantFields.BOOK_TITLE_FIELD) + " " + random.nextInt(1000);
    String publisher = fieldsNames.get(ConstantFields.PUBLISHER_FIELD) + " " + random.nextInt(1000);
    return new Book(id,
        author,
        title,
        publisher,
        random.nextInt(1000),
        random.nextInt(1000));
  }
}
