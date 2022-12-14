package com.task4.service;

import com.task4.model.Book;

public interface CatalogService {
    String getCatalog();
    Book getBook(Long id);
}
