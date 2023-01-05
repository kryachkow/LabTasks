package com.task2.util;

import com.task1.util.CustomList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

//Task 2 part 1
public class CopyOnWriteCustomList<E> extends CustomList<E> {

  public CopyOnWriteCustomList() {

  }

  @Override
  public Iterator<E> iterator() {
    return new CopyOnWriteIterator();
  }

  @Override
  public Iterator<E> customizableIterator(Predicate<E> choosingFunction) {
    return new CopyOnWriteIterator(choosingFunction);
  }

  private class CopyOnWriteIterator implements Iterator<E> {

    Object[] copiedElements;
    int cursor;
    int iterableSize;

    CopyOnWriteIterator() {
      this.copiedElements = CopyOnWriteCustomList.this.toArray();
      this.iterableSize = CopyOnWriteCustomList.this.size();
    }

    CopyOnWriteIterator(Predicate<E> choosingFunction) {
      this.copiedElements = CopyOnWriteCustomList.this.stream().filter(Objects::nonNull)
          .filter(choosingFunction).toArray();
      this.iterableSize = copiedElements.length;
    }

    @Override
    public boolean hasNext() {
      return cursor != iterableSize;
    }

    @Override
    public E next() {
      if (cursor >= iterableSize) {
        throw new NoSuchElementException();
      }
      int i = cursor;
      cursor++;
      return (E) copiedElements[i];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
      if (cursor >= iterableSize) {
        return;
      }
      while (cursor != iterableSize) {
        action.accept((E) copiedElements[cursor++]);
      }
    }
  }


}
