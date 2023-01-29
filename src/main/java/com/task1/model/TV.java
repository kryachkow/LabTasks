package com.task1.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class TV extends Electric{
    private int hertz;
    private int diagonal;

    public TV() {
    }

    public TV(int weight, String colour, int price, LocalDate dateOfManufacture, Period guaranteePeriod, int hertz, int diagonal) {
        super(weight, colour, price, dateOfManufacture, guaranteePeriod);
        this.hertz = hertz;
        this.diagonal = diagonal;
    }

    public int getHertz() {
        return hertz;
    }

    public void setHertz(int hertz) {
        this.hertz = hertz;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TV)) return false;
        if (!super.equals(o)) return false;
        TV tv = (TV) o;
        return getHertz() == tv.getHertz() && getDiagonal() == tv.getDiagonal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHertz(), getDiagonal());
    }

    @Override
    public String toString() {
        return "TV{" +
                "hertz=" + hertz +
                ", diagonal=" + diagonal +
                '}';
    }
}
