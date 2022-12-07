package com.task4.storage;

import com.task4.model.Book;
import com.task4.model.CartPart;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LastAddedStorage {
    private static final LinkedHashMap<Integer, CartPart> lastAddedLinkedHashMap = new LinkedHashMap<>();
    private static int counter;

    public static void addCartPart(Book book, int quantity){
        lastAddedLinkedHashMap.put(counter, new CartPart(book, quantity));
        counter++;
    }

    public static List<Map.Entry<Integer, CartPart>> getFiveLastEntries(){
        return lastAddedLinkedHashMap.entrySet().stream().filter(e -> e.getKey() >= counter - 5).collect(Collectors.toList());
    }
}
