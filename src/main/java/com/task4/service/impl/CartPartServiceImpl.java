package com.task4.service.impl;

import com.task4.model.Book;
import com.task4.model.CartPart;
import com.task4.service.CartPartService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartPartServiceImpl implements CartPartService {
    private final LinkedHashMap<Long, CartPart> lastAddedLinkedHashMap = new LinkedHashMap<>();
    private long counter;

    public CartPartServiceImpl(){};

    @Override
    public  void addCartPart(Book book, int quantity){
        lastAddedLinkedHashMap.put(counter, new CartPart(book, quantity));
        counter++;
    }

    @Override
    public List<Map.Entry<Long, CartPart>> getFiveLastEntries(){
        return lastAddedLinkedHashMap
                .entrySet()
                .stream()
                .filter(e -> e.getKey() >= counter - 5)
                .collect(Collectors.toList());
    }
}
