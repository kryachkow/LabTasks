package com.task3.util;

import com.task3.util.UniqueItemsArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniqueItemsArrayListTest {

    List<Integer> list;
    @BeforeEach
    void setUp() {
        list = new UniqueItemsArrayList<>();
        for (int i = 0; i < 10 ; i ++){
            list.add(i);
        }
    }

    @Test
    void set() {
        assertEquals(7, list.set(7, 11));
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 11, 8, 9}, list.toArray());
        assertThrows(IllegalArgumentException.class, () -> list.set(0, 1));
    }

    @Test
    void testAdd() {
        assertTrue(list.add(10));
        assertFalse(list.add(0));
    }

    @Test
    void testAddByIndex() {
        list.add(5, 10);
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 10, 5, 6, 7, 8, 9}, list.toArray());
        assertThrows(IllegalArgumentException.class, () -> list.add(5, 7));
    }

    @Test
    void testAddAll() {
        List<Integer> listToAdd = Arrays.asList(11, 12, 13, 15, 8, 6, 8, 4, null);
        List<Integer> listToThrowFalse = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertTrue(list.addAll(listToAdd));
        assertFalse(list.addAll(listToThrowFalse));
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 15, null}, list.toArray());
    }

    @Test
    void testAddAllByIndex() {
        List<Integer> listToAdd = Arrays.asList(11, 12, 13, 15, 8, 6, 8, 4, null);
        List<Integer> listToThrowFalse = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertTrue(list.addAll(1, listToAdd));
        assertFalse(list.addAll(1, listToThrowFalse));
        assertArrayEquals(new Integer[]{0, 11, 12, 13, 15, null, 1, 2, 3, 4, 5, 6, 7, 8, 9}, list.toArray());
    }
}