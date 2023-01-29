package com.store.strategy;

import com.store.model.Book;

public interface BookGenerationStrategy {

  Book getBook(long id);
}
