package com.task3.model;

import java.util.Objects;

public class SimpleItem {
    private int weight;
    private String name;

    public SimpleItem(){}

    public SimpleItem(int weight, String name){
        this.weight = weight;
        this.name = name;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleItem)) return false;
        SimpleItem that = (SimpleItem) o;
        return getWeight() == that.getWeight() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeight(), getName());
    }

    @Override
    public String toString() {
        return "SimpleItem{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
