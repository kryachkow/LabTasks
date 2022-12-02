package com.task3.util;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniqueItemsIterationArrayListTest  {
    static List<Integer> list;

    static {
        list = new UniqueItemsIterationArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        list.add(null);
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        list.add(null);
    }

    @Test
    void iterator() {
        Iterator<Integer> itr = list.iterator();

        for(int i = 0; i < 10; i++){
            assertEquals(i, itr.next());
        }
        assertNull(itr.next());
        assertFalse(itr.hasNext());

        StringBuilder builder = new StringBuilder();
        list.iterator().forEachRemaining(builder::append);
        assertEquals("0123456789null", builder.toString());

        itr = list.iterator();
        itr.next();
        assertThrows(UnsupportedOperationException.class, itr::remove);
    }
}