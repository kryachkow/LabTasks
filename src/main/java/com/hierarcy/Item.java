package com.hierarcy;

import java.util.Objects;

public class Item {
    private int weight;
    private String colour;

    public Item(int weight, String colour) {
        this.weight = weight;
        this.colour = colour;
    }

    public Item() {
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getWeight() == item.getWeight() && getColour().equals(item.getColour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeight(), getColour());
    }
}
