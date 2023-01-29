package com.task3.util;


import java.util.ArrayList;
import java.util.Collection;

//Task 3 part 1
public class UniqueItemsArrayList<E> extends ArrayList<E> {

    public UniqueItemsArrayList() {
        super();
    }

    public UniqueItemsArrayList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public E set(int index, E element) {
        if(!this.contains(element)){
            return super.set(index, element);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean add(E e) {
        if(!this.contains(e)){
            return super.add(e);
        }
        return false;
    }

    @Override
    public void add(int index, E element) {
        if(!this.contains(element)){
            super.add(index, element);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int size = this.size();
        for(E element: c){
            if(!this.contains(element)){
                super.add(element);
            }
        }
        return size != this.size();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int size = this.size();
        for(E element: c){
            if(!this.contains(element)){
                super.add(index++, element);
            }
        }
        return size != this.size();
    }

}
