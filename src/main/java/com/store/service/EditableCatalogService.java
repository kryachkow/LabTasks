package com.store.service;

import com.store.model.Book;
import java.util.HashMap;

public interface EditableCatalogService extends CatalogService {

  HashMap<Long, Book> getCatalog();

  boolean addBook(Book book);

  long getNextId();
}
