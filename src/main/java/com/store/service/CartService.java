package com.store.service;

import com.store.model.Book;

import java.util.Map;
import java.util.Set;

public interface CartService {

  void addToCart(Book book, int quantity);

  void clear();

  Set<Map.Entry<Book, Integer>> getEntrySet();

  boolean isEmpty();
}
