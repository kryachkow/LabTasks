package com.store.service;

import com.store.model.Book;
import com.store.model.CartPart;

import java.util.List;
import java.util.Map;

public interface CartPartService {

  void addCartPart(Book book, int quantity);

  public List<Map.Entry<Long, CartPart>> getFiveLastEntries();
}
