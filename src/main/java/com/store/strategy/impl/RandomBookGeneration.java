package com.store.strategy.impl;

import com.store.model.Book;
import com.store.strategy.BookGenerationStrategy;
import java.util.Random;

public class RandomBookGeneration implements BookGenerationStrategy {

  private static final String AUTHOR = "Автор ";
  private static final String TITLE = "Книга ";
  private static final String PUBLISHER = "Видавництво ";

  @Override
  public Book getBook(long id) {
    Random random = new Random();
    String author = AUTHOR + random.nextInt(1000);
    String title = TITLE + random.nextInt(1000);
    String publisher = PUBLISHER + random.nextInt(1000);
    return new Book(id,
        author,
        title,
        publisher,
        random.nextInt(1000),
        random.nextInt(1000));
  }
}
