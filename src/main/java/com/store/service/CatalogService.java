package com.store.service;

import com.store.model.Book;

public interface CatalogService {

  String getCatalogToString();

  Book getBook(Long id);

}
