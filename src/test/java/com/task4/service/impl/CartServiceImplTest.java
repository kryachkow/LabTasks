package com.task4.service.impl;

import com.task4.model.Book;
import com.task4.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {

    CartService cartService;
    HashMap<Book, Integer> cart;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        cartService = new CartServiceImpl();
        Field cartMap = CartServiceImpl.class.getDeclaredField("cart");
        cartMap.setAccessible(true);
        cart = (HashMap<Book, Integer>) cartMap.get(cartService);
    }


    @Test
    void addToCart() throws IllegalAccessException {
        cartService.addToCart(new Book(), 1);
        cartService.addToCart(new Book(), 4);
        cartService.addToCart( new Book(1L,
                "Панас Мирний",
                "Хіба ревуть воли, як ясла повні",
                "Книграня Є",
                166,
                200)
                , 3);


        Assertions.assertEquals(2, cart.size());
        Assertions.assertEquals(5, cart.get(new Book()));
    }

    @Test
    void getEntrySet() {
        assertEquals(cartService.getEntrySet(), cart.entrySet());
        cart.put(new Book(), 5);
        assertEquals(cartService.getEntrySet(), cart.entrySet());
    }


    @Test
    void clear() {
        cartService.clear();
        Assertions.assertEquals(0, cart.size());
    }

    @Test
    void isEmpty() {
        Assertions.assertTrue(cartService.isEmpty());
        cart.put(new Book(), 1);
        Assertions.assertFalse(cartService.isEmpty());
    }
}