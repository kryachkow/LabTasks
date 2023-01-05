package com.task3.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UniqueItemsIterationArrayList<E> extends ArrayList<E> {


    @Override
    public Iterator<E> iterator() {
        return new UniqueItemsIterator<>();
    }

    //CopyOnWrite
    private class UniqueItemsIterator<E> implements Iterator<E>{


        final Object[] elements;
        int cursor;
        final int size;

        private UniqueItemsIterator(){
            elements = Arrays.stream(UniqueItemsIterationArrayList.this.toArray()).distinct().toArray();
            size = elements.length;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if(cursor >=  size){
                throw new NoSuchElementException();
            }
            int i = cursor;
            cursor++;
            return (E) elements[i];
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }
}
