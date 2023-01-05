package com.store.command;

import com.store.service.EditableCatalogService;
import com.store.strategy.BookGenerationStrategy;

public class AddToCatalogCommand implements Command {

  private final EditableCatalogService editableCatalogService;
  private final BookGenerationStrategy bookGenerationStrategy;

  private static final String BOOK_SUCCESSFULLY_ADDED = "Книгу додано!";
  private static final String BOOK_WAS_NOT_ADDED = "Книгу не додано";

  public AddToCatalogCommand(EditableCatalogService editableCatalogService,
      BookGenerationStrategy bookGenerationStrategy) {
    this.editableCatalogService = editableCatalogService;
    this.bookGenerationStrategy = bookGenerationStrategy;
  }


  @Override
  public String doCommand() {
    if (editableCatalogService
        .addBook(bookGenerationStrategy.getBook(editableCatalogService.getNextId()))) {
      return BOOK_SUCCESSFULLY_ADDED;
    }
    return BOOK_WAS_NOT_ADDED;
  }
}
