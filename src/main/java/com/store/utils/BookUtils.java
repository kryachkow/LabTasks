package com.store.utils;

import com.store.model.Book;

public class BookUtils {

  private BookUtils() {
  }

  private static final String BOOK_EQ_MESSAGE = " Книга = ";
  private static final String AUTHOR_EQ_MESSAGE = " Автор = ";
  private static final String PUBLISHER_EQ_MESSAGE = " Видавець = ";
  private static final String PAGE_QUANTITY_MESSAGE = " Кількість сторінок = ";
  private static final String PRICE_MESSAGE = " Ціна = ";
  private static final String BOOK_ID = "Порядковий номер книги ";

  public static String bookToCustomerString(Book book) {
    return BOOK_ID + book.getId()
        + BOOK_EQ_MESSAGE + book.getBookTitle()
        + AUTHOR_EQ_MESSAGE + book.getAuthor()
        + PUBLISHER_EQ_MESSAGE + book.getPublisher()
        + PAGE_QUANTITY_MESSAGE + book.getPageNumber()
        + PRICE_MESSAGE + book.getPrice();
  }
}
