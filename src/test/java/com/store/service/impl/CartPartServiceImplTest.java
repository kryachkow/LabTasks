package com.store.service.impl;

import com.store.model.Book;
import com.store.model.CartPart;
import com.store.service.CartPartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.*;

class CartPartServiceImplTest {

  CartPartService cartPartService;
  LinkedHashMap<Long, CartPart> lastAddedLinkedHashMap;

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    cartPartService = new CartPartServiceImpl();
    Field linkedHashMap = CartPartServiceImpl.class.getDeclaredField("lastAddedLinkedHashMap");
    linkedHashMap.setAccessible(true);
    lastAddedLinkedHashMap = (LinkedHashMap<Long, CartPart>) linkedHashMap.get(cartPartService);
  }

  @Test
  void addCartPart() {
    cartPartService.addCartPart(new Book(), 4);
    cartPartService.addCartPart(new Book(), 3);
    Assertions.assertEquals(2, lastAddedLinkedHashMap.size());
    Assertions.assertEquals(new CartPart(new Book(), 3), lastAddedLinkedHashMap.get(1L));
  }

  @Test
  void getFiveLastEntries() {
    List<CartPart> list = new ArrayList<>();
    list.add(new CartPart(new Book(), 3));
    list.add(new CartPart(new Book(), 4));
    list.add(new CartPart(new Book(), 5));
    list.add(new CartPart(new Book(), 6));
    list.add(new CartPart(new Book(), 7));

    for (long i = 2L; i < 7L; i++) {
      lastAddedLinkedHashMap.put(i, list.get((int) i - 2));
    }
    Assertions.assertTrue(cartPartService
        .getFiveLastEntries()
        .stream()
        .map(Map.Entry::getValue)
        .allMatch(list::remove));
    Assertions.assertEquals(0, list.size());
  }
}