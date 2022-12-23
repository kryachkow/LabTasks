package com.store.service.impl;

import com.store.model.Book;
import com.store.service.CartService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartServiceImpl implements CartService {

  private final HashMap<Book, Integer> cart = new HashMap<>();

  public CartServiceImpl() {
  }

  @Override
  public void addToCart(Book book, int quantity) {
    cart.merge(book, quantity, Integer::sum);
  }

  @Override
  public void clear() {
    cart.clear();
  }

  @Override
  public Set<Map.Entry<Book, Integer>> getEntrySet() {
    return cart.entrySet();
  }

  @Override
  public boolean isEmpty() {
    return cart.isEmpty();
  }
}
