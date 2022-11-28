package com.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CopyOnWriteCustomListTest extends CustomListTest {

    @Override
    @BeforeEach
    void setUp() {
        list = new CopyOnWriteCustomList<>();
        for (int i = 0; i < 10 ; i ++){
            list.add(i);
        }
    }

    @Override
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
        itr.next();
        assertThrows(UnsupportedOperationException.class, itr::remove);
        itr = list.iterator();
        itr.next();
        StringBuilder result = new StringBuilder();
        itr.forEachRemaining(e -> result.append(e).append(" "));
        assertEquals("1 2 3 4 5 6 7 8 9 ", result.toString());
        itr = list.iterator();
        list.add(0, 5);
        assertEquals(0, itr.next());
        assertArrayEquals(new Integer[]{5,0,1,2,3,4,5,6,7,8,9}, list.toArray());
    }

    @Override
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
        itr.next();
        assertThrows(UnsupportedOperationException.class, itr::remove);
        StringBuilder result = new StringBuilder();
        itr.forEachRemaining(e -> result.append(e).append(" "));
        assertEquals("2 4 6 8 ", result.toString());
        itr = list.customizableIterator(e -> e % 2 != 0);
        itr.next();
        list.add(1, 1);
        assertEquals(3, itr.next());
        assertArrayEquals(new Integer[]{0,1,1,2,3,4,5,6,7,8,9}, list.toArray());
    }


}