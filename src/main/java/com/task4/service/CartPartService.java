package com.task4.service;

import com.task4.model.Book;
import com.task4.model.CartPart;

import java.util.List;
import java.util.Map;

public interface CartPartService {
    void addCartPart(Book book, int quantity);
    public List<Map.Entry<Long, CartPart>> getFiveLastEntries();
}
