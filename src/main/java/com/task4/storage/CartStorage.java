package com.task4.storage;

import com.task4.model.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CartStorage {
    private static final HashMap<Book, Integer> cart = new HashMap<>();

    public static void addToCart(Book book, int quantity){
        cart.merge(book, quantity, Integer::sum);
    }

    public static void clear(){
        cart.clear();
    }

    public static Set<Map.Entry<Book, Integer>> getEntrySet(){
        return cart.entrySet();
    }

    public static boolean isEmpty(){
        return cart.isEmpty();
    }
}
