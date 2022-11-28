package com.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomListTest {
    CustomList<Integer> list;

    @BeforeEach
    void setUp() {
         list = new CustomList<>();
        for (int i = 0; i < 10 ; i ++){
            list.add(i);
        }
    }

    @Test
    void iterator() {
        Iterator<Integer> itr = list.iterator();
        int i = 0;
        while(itr.hasNext()){
            assertEquals(i, itr.next());
            i++;
        }
        assertThrows(NoSuchElementException.class, itr::next);
        itr = list.iterator();
        while (itr.hasNext()){
            itr.next();
            if(itr.hasNext()){
                itr.next();
                itr.remove();
            }
        }
        assertArrayEquals(new Integer[]{0, 2, 4, 6, 8}, list.toArray());
        itr = list.iterator();
        itr.next();
        StringBuilder result = new StringBuilder();
        itr.forEachRemaining(e -> result.append(e).append(" "));
        assertEquals("2 4 6 8 ", result.toString());
        itr = list.iterator();
        itr.next();
        list.add(1, 5);
        assertThrows(ConcurrentModificationException.class, itr::next);
    }

    @Test
    void customizableIterator() {
        Iterator<Integer> itr = list.customizableIterator(e -> e % 2 == 0);
        int i = 0;
        while(itr.hasNext()){
            assertEquals(i, itr.next());
            i += 2;
        }
        assertThrows(NoSuchElementException.class, itr::next);
        itr = list.customizableIterator(e -> e % 2 == 0);

        while (itr.hasNext()){
            itr.next();
            itr.remove();
        }
        assertArrayEquals(new Integer[]{1, 3, 5, 7, 9}, list.toArray());
        itr = list.customizableIterator(e -> e % 3 == 0);
        StringBuilder result = new StringBuilder();
        itr.next();
        itr.forEachRemaining(e -> result.append(e).append(" "));
        assertEquals("9 ", result.toString());

        itr = list.customizableIterator(e -> e % 2 != 0);
        itr.next();
        list.add(1, 5);
        assertThrows(ConcurrentModificationException.class, itr::next);
    }

    @Test
    void add() {
        assertTrue(list.add(12));
        assertTrue(list.add(55));
        assertEquals(12, list.size());
        assertTrue(list.contains(12));
        assertTrue(list.contains(55));
        assertEquals(10, list.indexOf(12));
        assertEquals(11, list.indexOf(55));
        assertTrue(list.add(null));
        assertEquals(12, list.indexOf(null));
    }

    @Test
    void remove() {
        assertFalse(list.remove((Integer) 12));
        assertTrue(list.remove((Integer) 9));
        assertTrue(list.remove((Integer) 1));
        assertEquals(8, list.size());
        list.add(null);
        assertTrue(list.remove(null));
    }

    @Test
    void addAll() {
        CustomList<Integer> oddList = getOddList();
        assertTrue(list.addAll(oddList));
        assertEquals(15, list.size());
        assertEquals(9, list.indexOf(9));
        assertEquals(14, list.lastIndexOf(9));
        assertFalse(list.addAll(new CustomList<>()));
    }

    @Test
    void testAddAll() {
        CustomList<Integer> oddList = getOddList();
        assertTrue(list.addAll(7, oddList));
        assertEquals(15, list.size());
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 1, 3, 5, 7, 9, 7, 8, 9}, list.toArray());
        assertFalse(list.addAll(5, new CustomList<>()));
    }

    @Test
    void removeAll() {
        CustomList<Integer> oddList = getOddList();
        assertTrue(list.removeAll(oddList));
        assertEquals(5, list.size());
        assertArrayEquals(new Integer[]{0, 2, 4, 6, 8}, list.toArray());
        assertFalse(list.removeAll(new CustomList<>()));
    }

    @Test
    void retainAll() {
        CustomList<Integer> oddList = getOddList();
        assertTrue(list.retainAll(oddList));
        assertEquals(5, list.size());
        assertArrayEquals(new Integer[]{1, 3, 5, 7, 9}, list.toArray());
        assertFalse(list.retainAll(oddList));
    }

    @Test
    void get() {
        assertEquals(0, list.get(0));
        assertEquals(6, list.get(6));
        list.remove(6);
        assertEquals(7, list.get(6));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(55));

    }

    @Test
    void testAdd() {
        list.add(0, 0);
        assertEquals(11, list.size());
        assertEquals(0, list.indexOf(0));
        assertEquals(1, list.lastIndexOf(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(55, 1));

    }

    @Test
    void testRemove() {
        assertEquals(0, list.remove(0));
        assertEquals(3, list.remove(2));
        assertEquals(8, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(60));
    }

    private CustomList<Integer> getOddList(){
        CustomList<Integer> oddList = new CustomList<>();
        for (int i = 1; i < 10; i = i+2){
            oddList.add(i);
        }
        return oddList;
    }
}