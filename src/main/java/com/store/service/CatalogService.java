package com.store.service;

import com.store.model.Book;
import java.util.List;

public interface CatalogService {

  String getCatalogToString();
  Book getBook(Long id);
  List<Book> getAllBooks();
}
