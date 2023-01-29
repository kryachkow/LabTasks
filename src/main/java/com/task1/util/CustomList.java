package com.task1.util;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private int modCount;


    public CustomList(){
        this.size = 0;
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomizableIterator();
    }

    public Iterator<E> customizableIterator(Predicate<E> choosingFunction){ return new CustomizableIterator(choosingFunction);}

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size){
            return (E[]) Arrays.copyOf(elements, size);
    }
        System.arraycopy(elements, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean add(E e) {
        capacityCheck(size + 1);
        modCount++;
        elements[size] = e;
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elements[index] == null) {
                    removeByIndex(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elements[index])) {
                    removeByIndex(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] array = c.toArray();
        for (Object o : array) {
            if (this.contains(o)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int additionalLength = c.size();
        capacityCheck(elements.length + additionalLength);
        System.arraycopy(c.toArray(), 0, elements, size, additionalLength);
        size += additionalLength;
        modCount++;
        return additionalLength != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        indexCheck(index);
        int additionalLength = c.size();
        capacityCheck(elements.length + additionalLength);
        int lengthToMove = size - index;
        if (lengthToMove > 0) {
            System.arraycopy(elements, index, elements, index + additionalLength, lengthToMove);
        }
        System.arraycopy(c.toArray(), 0, elements, index, additionalLength);
        size += additionalLength;
        modCount++;
        return additionalLength != 0;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] array = c.toArray();
        boolean changed = false;
        for (Object o : array) {
            changed = this.remove(o) || changed;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int startingSize = this.size;
        this.stream()
                .filter(e -> !c.contains(e))
                .collect(Collectors.toList())
                .forEach(this::remove);
        return startingSize - this.size > 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        indexCheck(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        indexCheck(index);
        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        indexCheck(index);
        capacityCheck(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        modCount++;
        size++;
    }

    @Override
    public E remove(int index) {
        indexCheck(index);
        E removedElement = (E) elements[index];
        removeByIndex(index);
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elements[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    private void capacityCheck(int sizeNeeded){
        if (elements.length < sizeNeeded){
            grow(sizeNeeded);
        }
    }

    private void grow(int sizeNeeded){
        if (sizeNeeded < 0) {
            throw new OutOfMemoryError();
        }
        int newCapacity = elements.length + (elements.length >> 1);
        if(newCapacity < sizeNeeded){
            newCapacity = sizeNeeded;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void removeByIndex(int index){
        modCount++;
        int moveLength = size - index - 1;
        if (moveLength > 0)
            System.arraycopy(elements, index+1, elements, index, moveLength);
        size--;
        elements[size] = null;

    }

    private void indexCheck(int index){
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private class CustomizableIterator implements Iterator<E> {

        int cursor;
        int customizedCursor;
        int lastReturned = -1;
        Predicate<E> choosingFunction;
        int iterableSize;
        int expectedModCount = modCount;

        CustomizableIterator() {
            this.iterableSize = size;
        }
        CustomizableIterator(Predicate<E> choosingFunction){
            this.choosingFunction = choosingFunction;
            this.iterableSize = (int) CustomList.this.stream().filter(Objects::nonNull).filter(choosingFunction).count();
        }

        @Override
        public boolean hasNext() {
            return customizedCursor != iterableSize;
        }

        @Override
        public E next() {
            modificationCheck();
            if (customizedCursor >= iterableSize){
                throw new NoSuchElementException();
            }
            customizableIteration();
            int i = cursor;
            if (i > elements.length){
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            customizedCursor++;
            lastReturned = i;
            return (E) elements[i];
        }

        @Override
        public void remove() {
            if (lastReturned < 0){
                throw new IllegalStateException();
            }
            modificationCheck();

            CustomList.this.remove(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
            expectedModCount = modCount;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            int size = CustomList.this.size;
            if (cursor >= size){
                return;
            }
            final Object[] elements  = CustomList.this.elements;
            while (cursor != size && modCount == expectedModCount) {
                customizableIteration();
                action.accept((E) elements[cursor++]);
                lastReturned = cursor -1;
            }
            modificationCheck();
        }

        private void customizableIteration(){
            if(choosingFunction != null){
                while (elements[cursor] == null || !choosingFunction.test((E) elements[cursor])){
                    cursor++;
                }
            }
        }

        private void modificationCheck() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }
}
