package com.task4.service;

import com.task4.model.Book;

import java.util.Map;
import java.util.Set;

public interface CartService {

     void addToCart(Book book, int quantity);

     void clear();

     Set<Map.Entry<Book, Integer>> getEntrySet();

     boolean isEmpty();
}
